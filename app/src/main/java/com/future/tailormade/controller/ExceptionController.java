package com.future.tailormade.controller;

import com.blibli.oss.command.exception.CommandValidationException;
import com.blibli.oss.common.response.Response;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.exceptions.UnauthorizedException;
import com.future.tailormade.payload.response.base.helper.ResponseHelper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<Object> handleThrowable(Throwable throwable) {
        return com.blibli.oss.common.response.ResponseHelper.status(
                HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public Response<Object> handleNotFoundException() {
        return ResponseHelper.notFound();
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Response<Object> handleUnauthorizedException() {
        return ResponseHelper.unauthorized();
    }

    @ExceptionHandler(CommandValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<Object> handleCommandValidationException(CommandValidationException exception) {
        HashMap<String, List<String>> errors = new HashMap<>();

        exception.getConstraintViolations().forEach(violation -> {
            for (String attribute : getAttributes(violation)) {
                errors.putIfAbsent(attribute, new ArrayList<>());
                errors.get(attribute).add(violation.getMessage());
            }
        });

        return com.blibli.oss.common.response.ResponseHelper.badRequest(errors);
    }

    private String[] getAttributes(ConstraintViolation<?> constraintViolation) {
        String[] values = (String[])
                constraintViolation.getConstraintDescriptor().getAttributes().get("path");
        if (values == null || values.length == 0) {
            return getAttributesFromPath(constraintViolation);
        }
        return values;
    }

    private String[] getAttributesFromPath(ConstraintViolation<?> constraintViolation) {
        Path path = constraintViolation.getPropertyPath();

        StringBuilder builder = new StringBuilder();
        path.forEach(node -> {
            if (node.getName() != null) {
                if (builder.length() > 0) {
                    builder.append(".");
                }

                builder.append(node.getName());
            }
        });

        return new String[]{builder.toString()};
    }
}
