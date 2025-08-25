package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends AgriRentalException {
    public InvalidRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
