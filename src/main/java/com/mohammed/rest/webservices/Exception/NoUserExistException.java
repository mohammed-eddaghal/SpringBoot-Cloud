package com.mohammed.rest.webservices.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoUserExistException extends RuntimeException {
    public NoUserExistException(String s) {
        super(s);
    }
}
