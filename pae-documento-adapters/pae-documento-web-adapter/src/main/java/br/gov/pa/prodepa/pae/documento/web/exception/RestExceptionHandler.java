package br.gov.pa.prodepa.pae.documento.web.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.gov.pa.prodepa.pae.documento.domain.exception.CustomFieldError;
import br.gov.pa.prodepa.pae.documento.domain.exception.DomainException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<CustomFieldError> errors = new ArrayList<>();
		errors.add(new CustomFieldError(null, ex.getMessage()));
		
		ValidationErrorDetails errorDetails = ValidationErrorDetails.builder()
				.timesamp(new Date().getTime())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("Invalid json input data")
				.detail("Invalid json input data")
				.developerMessage(ex.getClass().getName())
				.erros(errors)
				.build();
		
		return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(DomainException.class)
	public ResponseEntity<?> handleBusinessException(DomainException ex, WebRequest request){
		
		ValidationErrorDetails errorDetails = ValidationErrorDetails.builder()
				.timesamp(new Date().getTime())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("DomainException.")
				.detail("Bad Request.")
				.developerMessage(ex.getClass().getName())
				.erros(ex.getErrors())
				.build();
		
		//return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		return handleExceptionInternal(ex, errorDetails, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<CustomFieldError> errors = new ArrayList<>();
		for(FieldError fd : ex.getBindingResult().getFieldErrors()) {
			errors.add(new CustomFieldError(fd.getField(), fd.getDefaultMessage()));
		}
		
		ValidationErrorDetails errorDetails = ValidationErrorDetails.builder()
			.timesamp(new Date().getTime())
			.status(HttpStatus.BAD_REQUEST.value())
			.title("Field validation error")
			.detail("Request contain errors")
			.developerMessage(ex.getClass().getName())
			.erros(errors)
			.build();
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<ValidationErrorDetails> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		
		List<CustomFieldError> errors = new ArrayList<>();
		
		if(ex.getConstraintViolations() != null) {
			for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
				errors.add(new CustomFieldError(violation.getPropertyPath().toString(), violation.getMessage()));
			}
		}
		
		ValidationErrorDetails errorDetails = ValidationErrorDetails.builder()
				.timesamp(new Date().getTime())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("Field validation error")
				.detail("Request contain errors")
				.developerMessage(ex.getClass().getName())
				.erros(errors)
				.build();
			
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
