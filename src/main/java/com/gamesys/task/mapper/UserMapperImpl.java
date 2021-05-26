package com.gamesys.task.mapper;

import com.gamesys.task.dto.UserRegistrationRequest;
import com.gamesys.task.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author Mohannad Elmagharby
 * on 5/26/2021
 */
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userRegistrationRequestToUser(UserRegistrationRequest userRegistrationRequest) {
        User user = User.builder()
                .username(userRegistrationRequest.getUsername())
                .password(userRegistrationRequest.getPassword())
                .dateOfBirth(LocalDate.parse(userRegistrationRequest.getDateOfBirth()))
                .ssn(userRegistrationRequest.getSsn())
                .build();
        return user;
    }
}
