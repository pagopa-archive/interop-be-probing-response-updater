
package it.pagopa.interop.probing.response.updater.annotation.validator;

import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import it.pagopa.interop.probing.response.updater.annotation.ValidateStringArraySize;

public class StringArrayValidator
    implements ConstraintValidator<ValidateStringArraySize, String[]> {

  private int maxSize;

  @Override
  public void initialize(ValidateStringArraySize constraintAnnotation) {
    maxSize = constraintAnnotation.maxSize();
  }

  @Override
  public boolean isValid(String[] array, ConstraintValidatorContext context) {
    if (Objects.nonNull(array)) {
      for (String s : array) {
        if (s.length() > maxSize) {
          return false;
        }
      }
    }
    return true;
  }

}
