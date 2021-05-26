package com.gamesys.task.mapper;

import com.gamesys.task.dto.UserRegistrationRequest;
import com.gamesys.task.model.User;

/**
 * @author Mohannad Elmagharby
 * on 5/26/2021
 */
public interface UserMapper {
    User userRegistrationRequestToUser(UserRegistrationRequest userRegistrationRequest);
}
