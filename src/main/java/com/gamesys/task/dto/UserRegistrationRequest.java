package com.gamesys.task.dto;

import com.gamesys.task.utils.validators.IsISODate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Mohannad Elmagharby
 * on 5/26/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {

    @Pattern(regexp ="^[a-zA-Z0-9]{2,40}$",message = "username must be alphanumerical and has no spaces," +
            "username must be between(2-40) chars")
    @NotNull(message = "username must be not null")
    private String username;

    @Pattern(regexp="^[(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)]{4,40}$",
            message = "password must be at least four characters, at least one lower case character," +
                    "at least one upper case character, at least one number")
    private String password;

    @IsISODate(message = "dateOfBirth must be in (ISO 8601) format")
    private String dateOfBirth;

    @NotBlank(message = "ssn must be not blank")
    private String ssn;
}
