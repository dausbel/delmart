package com.example.usermanagment.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UserExceptions.class})
    protected ResponseEntity<Object> handleConflict(UserExceptions ex, WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder().mensaje(ex.getMensaje()).build(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {

        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<Object> invalidValues = constraintViolations.stream()
            .map(ConstraintViolation::getInvalidValue)
            .collect(Collectors.toList());
        List<String> errorMessages = constraintViolations.stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.toList());
        log.error("handleConstraintViolationException: invalidValues={} errors={}", invalidValues, errorMessages);
        return new ResponseEntity<>(ErrorResponse.builder().mensaje(errorMessages.get(0)).build(),
                                    HttpStatus.BAD_REQUEST);
    }


    @Override
    protected final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errorMessages = ex.getBindingResult()
            .getAllErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());
        log.error("handleMethodArgumentNotValid: errors=[{}]", errorMessages);
        return new ResponseEntity<>(ErrorResponse.builder().mensaje(errorMessages.get(0)).build(),
                                    HttpStatus.BAD_REQUEST);
    }
}
