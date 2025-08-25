package com.rent.agri.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.rent.agri.validation.PaymentModeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = PaymentModeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPaymentMode {
    String message() default "Payment mode must be one of: UPI, CARD, CASH, NET_BANKING";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
