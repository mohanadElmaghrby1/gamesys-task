package com.gamesys.task.service;

import com.gamesys.task.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.gamesys.task.config.UsersDataProviderConfiguration.USERS_BLACK_LIST;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Mohannad Elmagharby
 * on 5/26/2021
 */
@Service
public class ExclusionServiceImpl implements ExclusionService {

    private Set<User> usersBlackList;

    public ExclusionServiceImpl(@Qualifier(USERS_BLACK_LIST)Set<User> usersBlackList) {
        this.usersBlackList = usersBlackList;
    }

    @Override
    public boolean validate(String dateOfBirth, String ssn) {
        final User user = User.builder()
                .dateOfBirth(LocalDate.parse(dateOfBirth))
                .ssn(ssn).build();

        return !usersBlackList.contains(user);
    }
}
