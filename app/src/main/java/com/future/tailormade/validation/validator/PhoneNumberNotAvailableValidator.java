package com.future.tailormade.validation.validator;

import com.future.tailormade.repository.UserRepository;
import com.future.tailormade.validation.PhoneNumberNotAvailable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PhoneNumberNotAvailableValidator implements ConstraintValidator<PhoneNumberNotAvailable, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(PhoneNumberNotAvailable constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return !userRepository.existsByPhoneNumber(value).block();
    }
}
