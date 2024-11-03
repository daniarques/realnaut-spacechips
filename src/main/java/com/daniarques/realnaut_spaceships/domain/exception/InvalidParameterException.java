package com.daniarques.realnaut_spaceships.domain.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidParameterException extends RuntimeException {

    private final String message;

    @Override
    public String getMessage() {
        return this.message;
    }
}
