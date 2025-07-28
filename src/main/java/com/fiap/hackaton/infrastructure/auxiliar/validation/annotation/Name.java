package com.fiap.hackaton.infrastructure.auxiliar.validation.annotation;

import com.fiap.hackaton.infrastructure.auxiliar.validation.annotationClasses.NameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
    String message() default "Coloque um nome válido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
