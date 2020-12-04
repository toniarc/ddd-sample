package br.gov.pa.prodepa.pae.documento.domain.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DomainException extends RuntimeException{
	
	private static final long serialVersionUID = -6175290711300280908L;

	private List<CustomFieldError> errors = new ArrayList<>();
	
	public DomainException() {
	}

	public DomainException(String message) {
		super(message);
		errors.add(new CustomFieldError(null, message));
	}
	
	public DomainException(List<CustomFieldError> errors) {
		this.errors.addAll(errors);
	}
	
	public DomainException(String message, List<CustomFieldError> errors) {
		super(message);
		this.errors.addAll(errors);
	}

	public List<CustomFieldError> getErrors() {
		return errors;
	}
	
	public void addError(String mensagem) {
		CustomFieldError customFieldError = new CustomFieldError(null, mensagem);
		if(!errors.contains(customFieldError)) {
			errors.add(customFieldError);
		}
	}
	
	public void addError(String codigo, String mensagem) {
		CustomFieldError customFieldError = new CustomFieldError(codigo, mensagem);
		if(!errors.contains(customFieldError)) {
			errors.add(customFieldError);
		}
	}

	public void throwException() {
		if(this.errors.size() > 0 ) {
			throw new DomainException(String.join(",", errors.stream().map(e -> e.getMessage()).collect(Collectors.toList())), this.errors);
		}
	}

	@Override
	public String toString() {
		return String.join(",", errors.stream().map(e -> e.getMessage()).collect(Collectors.toList()));
	}
	
	
}

