package com.gamesys.task.error.exceptions;

/**
 * @author Mohannad Elmagharby
 * on 5/26/2021
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
