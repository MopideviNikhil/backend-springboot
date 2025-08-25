package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public abstract class AgriRentalException extends RuntimeException {
    private final HttpStatus status;

    public AgriRentalException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
