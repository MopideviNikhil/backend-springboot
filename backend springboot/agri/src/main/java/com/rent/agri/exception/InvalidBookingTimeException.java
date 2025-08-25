package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class InvalidBookingTimeException extends AgriRentalException {
    public InvalidBookingTimeException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
