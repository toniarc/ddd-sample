package br.gov.pa.prodepa.pae.documento.domain.test.unit;

import br.gov.pa.prodepa.pae.documento.domain.exception.ObjectStorageException;
import br.gov.pa.prodepa.pae.documento.domain.port.ObjectStoragePort;

public class ObjectStoragePortMock implements ObjectStoragePort{

	public ObjectStoragePortMock() {
	}
	
	@Override
	public String putObject(byte[] file, String contentType) throws ObjectStorageException {
		return "key01";
	}
	
	@Override
	public byte[] getObject(String id) throws ObjectStorageException {
		return "teste".getBytes();
	}
	
	@Override
	public void deleteObject(String id) throws ObjectStorageException {
	}
}
