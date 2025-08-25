package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class PaymentFailedException extends AgriRentalException {
    public PaymentFailedException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}

