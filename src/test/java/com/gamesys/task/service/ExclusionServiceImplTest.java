package com.gamesys.task.service;

import com.gamesys.task.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exclusion User Tests")
class ExclusionServiceImplTest {

    private Set<User> usersBlackList;
    private ExclusionService exclusionService;

    @BeforeEach
    void setUp() {
        usersBlackList = ConcurrentHashMap.newKeySet();
        exclusionService = new ExclusionServiceImpl(usersBlackList);
    }

    @Test
    @DisplayName("when validate unblocked user should return true")
    void whenValidateUnBlockedUserReturnTrue(){
        assertTrue(exclusionService.validate("1996-02-20" , "1234"));
    }

    @Test
    @DisplayName("when validate unblocked user with the same dateOfBirth of Blocked user should return true")
    void whenValidateUnBlockedUserWithSameDateOfBirthReturnTrue(){

        String sameBirthDate = "1996-02-20";
        User blockedUser = User.builder()
                .dateOfBirth(LocalDate.parse(sameBirthDate))
                .ssn("12345")
                .build();

        usersBlackList.add(blockedUser);

        assertTrue(exclusionService.validate(sameBirthDate , "newsssn"));
    }

    @Test
    @DisplayName("when validate blocked user should return false")
    void whenValidateUnBlockedUserReturnFalse(){
        User blockedUser = User.builder()
                .dateOfBirth(LocalDate.parse("1996-02-20"))
                .ssn("12345")
                .build();

        usersBlackList.add(blockedUser);

        assertFalse(exclusionService.validate(blockedUser.getDateOfBirth().toString() , blockedUser.getSsn()));
    }


}
