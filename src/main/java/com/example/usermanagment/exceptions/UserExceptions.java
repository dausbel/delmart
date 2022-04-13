package com.example.usermanagment.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserExceptions extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public UserExceptions(final String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
