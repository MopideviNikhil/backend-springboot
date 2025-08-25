package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class TimeException extends AgriRentalException {
    public TimeException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}

