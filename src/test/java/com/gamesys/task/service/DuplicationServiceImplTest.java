package com.gamesys.task.service;

import com.gamesys.task.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Duplication User Tests")
class DuplicationServiceImplTest {
    private Set<User> userRegisteredList;
    private DuplicationService duplicationService;

    @BeforeEach
    void setUp() {
        userRegisteredList = ConcurrentHashMap.newKeySet();
        duplicationService = new DuplicationServiceImpl(userRegisteredList);
    }

    @Test
    @DisplayName("when validate new user should return true")
    void whenValidateNewUserReturnTrue(){
        assertTrue(duplicationService.validate("1996-02-20" , "1234"));
    }

    @Test
    @DisplayName("when validate already exists user should return false")
    void whenValidateAlreadyExistsUserReturnFalse(){
        User registeredUser = User.builder()
                .dateOfBirth(LocalDate.parse("1996-02-20"))
                .ssn("4321432")
                .build();

        userRegisteredList.add(registeredUser);

        assertFalse(duplicationService.validate(registeredUser.getDateOfBirth().toString() , registeredUser.getSsn()));
    }


    @Test
    @DisplayName("when validate already exists user with same (dateOfBirth and ssn) but different usernames should return false")
    void whenValidateAlreadyExistsUserWithSameDateOfBirthAndSsnReturnFalse(){
        User registeredUser = User.builder()
                .username("mohannad")
                .password("Aaaa23")
                .dateOfBirth(LocalDate.parse("1996-02-20"))
                .ssn("4321432")
                .build();

        User newUserWithSameSsnAndDateOfbirth = User.builder()
                .username("gamesys")
                .password("Gamesys20")
                .dateOfBirth(LocalDate.parse("1996-02-20"))
                .ssn("4321432")
                .build();

        userRegisteredList.add(registeredUser);

        assertFalse(duplicationService.validate(newUserWithSameSsnAndDateOfbirth.getDateOfBirth().toString() ,
                newUserWithSameSsnAndDateOfbirth.getSsn()));
    }


}
