package com.rent.agri.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.rent.agri.validation.GenderEnumValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = GenderEnumValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGender {
    String message() default "Gender must be Male, Female, or Other";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
