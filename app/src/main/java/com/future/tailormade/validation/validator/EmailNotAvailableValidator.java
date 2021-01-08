package com.future.tailormade.validation.validator;

import com.future.tailormade.repository.UserRepository;
import com.future.tailormade.validation.EmailNotAvailable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class EmailNotAvailableValidator implements ConstraintValidator<EmailNotAvailable, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(EmailNotAvailable constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return !userRepository.existsByEmail(value).block();
    }
}
