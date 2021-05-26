package com.gamesys.task.model;

import lombok.*;

import java.time.LocalDate;

/**
 * @author Mohannad Elmagharby
 * on 5/26/2021
 */
@Builder
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = {"dateOfBirth", "ssn"})
@ToString(exclude = {"password"})
public class User {
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private String ssn;

}
