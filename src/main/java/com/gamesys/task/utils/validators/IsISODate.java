package com.gamesys.task.utils.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Mohannad Elmagharby
 * on 5/26/2021
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsISODateValidator.class)
public @interface IsISODate {
    String message() default "Date must be in (ISO 8601) format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
