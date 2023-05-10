package it.pagopa.interop.probing.response.updater.config.mapper;

import java.io.IOException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;

public class BeanDeserializerWithValidation extends BeanDeserializer {

  private static final long serialVersionUID = 1L;

  protected BeanDeserializerWithValidation(BeanDeserializerBase src) {
    super(src);
  }

  @Override
  public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    Object instance = super.deserialize(p, ctxt);
    validate(instance);
    return instance;
  }

  <T> void validate(T t) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<T>> violations = validator.validate(t);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

}
