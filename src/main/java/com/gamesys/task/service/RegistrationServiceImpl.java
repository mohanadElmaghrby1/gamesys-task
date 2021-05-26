package com.gamesys.task.service;

import com.gamesys.task.dto.UserRegistrationRequest;
import com.gamesys.task.error.exceptions.UserAlreadyExistsException;
import com.gamesys.task.error.exceptions.UserBlockedException;
import com.gamesys.task.mapper.UserMapper;
import com.gamesys.task.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.gamesys.task.config.UsersDataProviderConfiguration.USERS_REGISTERED_LIST;
import java.util.Set;

/**
 * @author Mohannad Elmagharby
 * on 5/26/2021
 */
@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    private ExclusionService exclusionService;
    private DuplicationService duplicationService;
    private UserMapper userMapper;
    private Set<User> usersRegisteredList;

    public RegistrationServiceImpl(ExclusionService exclusionService, DuplicationService duplicationService,
                                   UserMapper userMapper,@Qualifier(USERS_REGISTERED_LIST) Set<User> usersRegisteredList) {
        this.exclusionService = exclusionService;
        this.duplicationService = duplicationService;
        this.userMapper = userMapper;
        this.usersRegisteredList = usersRegisteredList;
    }


    @Override
    public User register(UserRegistrationRequest userRegistrationRequest) {
        //check if user is blocked
        if (!this.exclusionService.validate(userRegistrationRequest.getDateOfBirth(),userRegistrationRequest.getSsn())){
            log.error("User {} is blocked",userRegistrationRequest);
            throw new UserBlockedException("User is blocked");
        }

        //check if user is already exists
        if (!this.duplicationService.validate(userRegistrationRequest.getDateOfBirth(),userRegistrationRequest.getSsn())){
            log.error("User {} already exists",userRegistrationRequest);
            throw new UserAlreadyExistsException("User already exists");
        }

        User userToRegister = this.userMapper.userRegistrationRequestToUser(userRegistrationRequest);
        this.usersRegisteredList.add(userToRegister);
        log.info("New User {} registered",userToRegister);

        return userToRegister;
    }
}
