package com.gamesys.task.service;

import com.gamesys.task.dto.UserRegistrationRequest;
import com.gamesys.task.model.User;
import org.springframework.http.ResponseEntity;

/**
 * @author Mohannad Elmagharby
 * on 5/26/2021
 */
public interface RegistrationService {
    User register(UserRegistrationRequest userRegistrationRequest);
}
