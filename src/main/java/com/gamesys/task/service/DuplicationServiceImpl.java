package com.gamesys.task.service;

import com.gamesys.task.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

import static com.gamesys.task.config.UsersDataProviderConfiguration.USERS_REGISTERED_LIST;

/**
 * @author Mohannad Elmagharby
 * on 5/26/2021
 */
@Service
public class DuplicationServiceImpl implements DuplicationService {
    private Set<User> userRegisteredList;

    public DuplicationServiceImpl(@Qualifier(USERS_REGISTERED_LIST)Set<User> userRegisteredList) {
        this.userRegisteredList = userRegisteredList;
    }

    @Override
    public boolean validate(String dateOfBirth, String ssn) {
        final User user = User.builder()
                .dateOfBirth(LocalDate.parse(dateOfBirth))
                .ssn(ssn).build();

        return !userRegisteredList.contains(user);
    }
}
