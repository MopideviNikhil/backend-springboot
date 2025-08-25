package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class SqlOperationException extends AgriRentalException {
    public SqlOperationException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

