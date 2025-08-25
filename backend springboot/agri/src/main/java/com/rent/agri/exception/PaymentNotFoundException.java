package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class PaymentNotFoundException extends AgriRentalException {
    public PaymentNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

