package br.gov.pa.prodepa.pae.documento.domain.exception;

public class ObjectStorageException extends Exception {

	private static final long serialVersionUID = 5165549434574778771L;
	
	public ObjectStorageException(String msg, Exception ex) {
		super(msg, ex);
	}

}
