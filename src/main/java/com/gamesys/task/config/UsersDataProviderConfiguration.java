package com.gamesys.task.config;

import com.gamesys.task.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

/**
 * @author Mohannad Elmagharby
 * on 5/26/2021
 */
@Configuration
public class UsersDataProviderConfiguration {
    public static final String USERS_REGISTERED_LIST = "USERS_REGISTERED_LIST";
    public static final String USERS_BLACK_LIST = "USERS_BLACK_LIST";

    @Bean(USERS_REGISTERED_LIST)
    @Scope(value = SCOPE_SINGLETON)
    public Set<User> usersRegisteredList(){
        //thread safe HashSet
        return ConcurrentHashMap.newKeySet();
    }


    @Bean(USERS_BLACK_LIST)
    @Scope(value = SCOPE_SINGLETON)
    public Set<User> usersBlackList(){
        //thread safe HashSet
        Set<User> set=  ConcurrentHashMap.newKeySet();
        initBlackList(set);
        return set;
    }


    private void initBlackList(Set<User> usersBlackList) {
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

        usersBlackList.add(adaLovelace);
        usersBlackList.add(alanTuring);
        usersBlackList.add(konradZuse);
    }
}
