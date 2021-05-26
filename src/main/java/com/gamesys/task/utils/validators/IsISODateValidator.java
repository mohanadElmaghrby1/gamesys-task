package com.gamesys.task.utils.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author Mohannad Elmagharby
 * on 5/26/2021
 */
public class IsISODateValidator implements ConstraintValidator<IsISODate, String> {

    public void initialize(IsISODate constraint) {
    }

    public boolean isValid(String date, ConstraintValidatorContext context) {
        try {
            return date != null && LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE) != null;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
