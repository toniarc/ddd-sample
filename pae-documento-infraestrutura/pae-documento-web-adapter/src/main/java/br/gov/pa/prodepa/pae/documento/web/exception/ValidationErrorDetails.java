package br.gov.pa.prodepa.pae.documento.web.exception;

import java.util.List;

import br.gov.pa.prodepa.pae.documento.domain.exception.CustomFieldError;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationErrorDetails {
	
	private String title;
	private int status;
	private String detail;
	private Long timesamp;
	private String developerMessage;
	private List<CustomFieldError> erros;

	public ValidationErrorDetails(String title, int status, String detail, Long timesamp, String developerMessage, List<CustomFieldError> erros) {
		this.title = title;
		this.status = status;
		this.detail = detail;
		this.timesamp = timesamp;
		this.developerMessage = developerMessage;
		this.erros = erros;
		
	}

}
