package com.webapp.tas.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NotUniqueExcetpion extends RuntimeException {
    public NotUniqueExcetpion(String message) {
        super(message);
    }
}
