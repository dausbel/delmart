package com.example.usermanagment.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserExceptions extends RuntimeException {
    private String mensaje;
    private HttpStatus httpStatus;

    public UserExceptions(final String mensaje, HttpStatus httpStatus) {
        super(mensaje);
        this.httpStatus = httpStatus;
        this.mensaje = mensaje;
    }
}
