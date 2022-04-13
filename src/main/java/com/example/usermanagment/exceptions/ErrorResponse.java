package com.example.usermanagment.exceptions;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private final String mensaje;

}
