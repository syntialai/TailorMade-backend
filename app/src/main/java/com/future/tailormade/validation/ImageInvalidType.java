package com.future.tailormade.validation;

import com.future.tailormade.validation.validator.ImageInvalidTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE, METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {ImageInvalidTypeValidator.class})
@Documented
public @interface ImageInvalidType {

    String message() default "InvalidFormat";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] path() default {};
}
