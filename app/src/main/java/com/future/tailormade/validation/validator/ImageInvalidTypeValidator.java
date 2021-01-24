package com.future.tailormade.validation.validator;

import com.future.tailormade.constants.BaseConstants;
import com.future.tailormade.validation.ImageInvalidType;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ImageInvalidTypeValidator implements ConstraintValidator<ImageInvalidType, String> {

    @Override
    public void initialize(ImageInvalidType constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return value.contains(BaseConstants.PART_SEPARATOR);

//        String encodedImage = getEncodedBase64Image(value);
//        return Pattern.matches(BaseConstants.REGEX_IMAGE_BASE64, value);
    }

    private String getEncodedBase64Image(String base64File) {
        return base64File.split(BaseConstants.PART_SEPARATOR)[1];
    }
}
