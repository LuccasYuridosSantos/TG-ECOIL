package com.project.tgecoil.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceDuplicatedException extends RuntimeException {

    public ResourceDuplicatedException(String message) {
        super(message);
    }

    public ResourceDuplicatedException(final String message,
                                       final Object... params) {
        super(String.format(message, params));
    }
}
