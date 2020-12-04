package br.gov.pa.prodepa.pae.documento.domain.common;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.gov.pa.prodepa.pae.documento.domain.exception.CustomFieldError;
import br.gov.pa.prodepa.pae.documento.domain.exception.DomainException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class SelfValidating<T> {

  private Validator validator;

  public SelfValidating() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  /**
   * Evaluates all Bean Validations on the attributes of this
   * instance.
   */
  public void validateSelf() {
    Set<ConstraintViolation<T>> violations = validator.validate((T) this);
    if (!violations.isEmpty()) {
      //throw new ConstraintViolationException(violations);
    	List<CustomFieldError> errors = violations.stream().map( v -> new CustomFieldError(v.getPropertyPath().toString() , v.getMessage())).collect(Collectors.toList());
    	new DomainException(errors).throwException();
    }
  }
}
