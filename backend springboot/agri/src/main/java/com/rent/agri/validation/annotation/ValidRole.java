package com.rent.agri.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.rent.agri.validation.RoleValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = RoleValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRole {
    String message() default "Invalid role, Role must be Admin, Vender, Farmer";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
