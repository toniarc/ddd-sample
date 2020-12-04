package br.gov.pa.prodepa.pae.documento.domain.port;

import br.gov.pa.prodepa.pae.documento.domain.exception.ObjectStorageException;

public interface ObjectStoragePort {

	byte[] getObject(String id) throws ObjectStorageException;
	
	void deleteObject(String id) throws ObjectStorageException;

	String putObject(byte[] file, String contentType) throws ObjectStorageException;
}
