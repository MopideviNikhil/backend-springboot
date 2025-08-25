package com.rent.agri.validation;

import java.util.Arrays;

import com.rent.agri.model.enums.PaymentMode;
import com.rent.agri.validation.annotation.ValidPaymentMode;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PaymentModeValidator implements ConstraintValidator<ValidPaymentMode, PaymentMode> {

    @Override
    public boolean isValid(PaymentMode value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; 
        }
        return Arrays.asList(PaymentMode.values()).contains(value);
    }
}
