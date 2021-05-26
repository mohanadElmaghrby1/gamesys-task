package com.gamesys.task.service;

/**
 * @author Mohannad Elmagharby
 * on 5/26/2021
 */
public interface DuplicationService {
    /**
     * Validates a user against an list using their date of
     * birth and social security number as identifier.
     *
     * @param dateOfBirth the user's date of birth in ISO 8601 format
     * @param ssn the user's social security number (United States)
     * @return true if the user is not exists
     */
    boolean validate(String dateOfBirth, String ssn);
}
