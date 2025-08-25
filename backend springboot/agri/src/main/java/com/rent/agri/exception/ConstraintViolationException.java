package com.rent.agri.exception;

import org.springframework.http.HttpStatus;

public class ConstraintViolationException extends AgriRentalException {


	private static final long serialVersionUID = 1L;

	public ConstraintViolationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }


}
