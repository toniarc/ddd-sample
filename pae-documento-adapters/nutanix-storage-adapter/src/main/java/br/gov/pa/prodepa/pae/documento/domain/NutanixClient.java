package br.gov.pa.prodepa.pae.documento.domain;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import br.gov.pa.prodepa.pae.documento.domain.exception.ObjectStorageException;
import br.gov.pa.prodepa.pae.documento.domain.port.ObjectStoragePort;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

@Component
public class NutanixClient implements ObjectStoragePort {

	private static final String ACCESS_KEY_ID = "B6t10ZUFP9NazpXWNx12v0HsrhJHxUna";
	private static final String SECRET_ACCESS_KEY = "qC0Y80NyEsjUkvJHIHu_NOBPbpsj-6Zz";
	
	private static final String BUCKET = "pae4"; 
	private final String ENDPOINT_URL = "https://poc-s3ntnx.prodepa.pa.gov.br";
	private final MinioClient minioClient;
	
	public NutanixClient() {
		
		try {
			minioClient = criarClienteMinio();
			boolean found = verificarSeTodosOsBucketsJaForamCriados();
			
			if(!found) {
				minioClient.makeBucket(MakeBucketArgs.builder().bucket("pae04").build()); 
			}
			
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IllegalArgumentException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private MinioClient criarClienteMinio() {
		return MinioClient.builder()
	              .endpoint(ENDPOINT_URL)
	              .credentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY)
	              .build();
	}
	
	private boolean verificarSeTodosOsBucketsJaForamCriados() {
		try {
			return minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET).build());
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IllegalArgumentException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String putObject(byte[] file, String contentType) throws ObjectStorageException {

			String id = UUID.randomUUID().toString();
			ByteArrayInputStream bais = new ByteArrayInputStream(file);

			try {
				minioClient.putObject(
						PutObjectArgs.builder()
							.bucket(BUCKET)
							.object(id)
							.contentType(contentType)
							.stream(bais, bais.available(), -1)
							.build());
				return id;
			} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
					| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
					| IllegalArgumentException | IOException e) {
				throw new ObjectStorageException("Ocorreu um erro durante o upload do arquivo", e);
			} finally {
				try {
					if(bais != null)
						bais.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

	}

	@Override
	public byte[] getObject(String id) throws ObjectStorageException {
		
		InputStream stream = null;;
		
		try {
			stream = minioClient.getObject(
					  GetObjectArgs.builder()
					  .bucket(BUCKET)
					  .object(id)
					  .build());
			
			return IOUtils.toByteArray(stream);
		} catch (ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IllegalArgumentException | IOException e) {
			if( e instanceof ErrorResponseException) {
				ErrorResponseException ex = (ErrorResponseException) e;
				if(ex.errorResponse().code().equals("NoSuchKey")) {
					throw new ObjectStorageException("A chave informada não existe", e);
				}
			}
			throw new ObjectStorageException("Ocorreu um erro durante o upload do arquivo", e);
		} catch(InvalidKeyException e){
			throw new ObjectStorageException("A chave informada não exite", e);
		} finally {
			try {
				if(stream != null)
					stream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@Override
	public void deleteObject(String id) throws ObjectStorageException {
		try {
			minioClient.removeObject(RemoveObjectArgs.builder().bucket(BUCKET).object(id).build());
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IllegalArgumentException | IOException e) {
			throw new ObjectStorageException("Ocorreu um erro ao remover o objecto", e);
		}
	}

	public void criarNovoBucket(String bucketName) {
		try {
			minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
		} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
				| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
				| IllegalArgumentException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}
