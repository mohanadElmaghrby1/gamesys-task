package com.gamesys.task.config;

import com.gamesys.task.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static com.gamesys.task.config.UsersDataProviderConfiguration.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@DisplayName("Test users data providers")
class UsersDataProviderConfigurationTest {

    @Autowired
    @Qualifier(USERS_REGISTERED_LIST)
    Set<User> usersRegisteredList;


    @Autowired
    @Qualifier(USERS_BLACK_LIST)
    Set<User> usersBlackList;


    @Test
    void usersRegisteredListSizeShouldBeEmpty(){
        assertEquals(usersRegisteredList.size(), 0);
    }

    @Test
    void usersBlackListSizeShouldBeThree(){
        assertEquals(usersBlackList.size(), 3);
    }

    @Test
    void blockedUsersShouldBeThereInTheBlackList(){
        final User adaLovelace = User.builder()
                .username("adaLovelace")
                .password("Analytical3ngineRulz")
                .dateOfBirth(LocalDate.parse("1815-12-10", DateTimeFormatter.ISO_LOCAL_DATE))
                .ssn("85385075")
                .build();

        final User alanTuring = User.builder()
                .username("alanTuring")
                .password("eniGmA123")
                .dateOfBirth(LocalDate.parse("1912-06-23", DateTimeFormatter.ISO_LOCAL_DATE))
                .ssn("123456789")
                .build();

        final User konradZuse = User.builder()
                .username("konradZuse")
                .password("zeD1")
                .dateOfBirth(LocalDate.parse("1910-06-22", DateTimeFormatter.ISO_LOCAL_DATE))
                .ssn("987654321")
                .build();

        assertTrue(usersBlackList.contains(adaLovelace));
        assertTrue(usersBlackList.contains(alanTuring));
        assertTrue(usersBlackList.contains(konradZuse));
    }


}
