package com.future.tailormade.validation.validator;

import com.future.tailormade.constants.BaseConstants;
import com.future.tailormade.validation.ImageInvalidType;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class ImageInvalidTypeValidator implements ConstraintValidator<ImageInvalidType, String> {

    @Override
    public void initialize(ImageInvalidType constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return Pattern.matches(BaseConstants.REGEX_IMAGE_BASE64, value);
    }
}
