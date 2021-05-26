package com.gamesys.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamesys.task.dto.UserRegistrationRequest;
import com.gamesys.task.error.exceptions.UserAlreadyExistsException;
import com.gamesys.task.error.exceptions.UserBlockedException;
import com.gamesys.task.service.DuplicationService;
import com.gamesys.task.service.ExclusionService;
import com.gamesys.task.service.RegistrationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {RegistrationController.class})
class RegistrationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegistrationServiceImpl registrationService;


    @Test
    @DisplayName("Should have username alphanumerical and has no spaces")
    void whenRegisterUserWithInvalidUsernameThenFailAndStatusCode400() throws Exception {
        final UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setUsername("i n v");
        userRegistrationRequest.setPassword("abcdAa1");
        userRegistrationRequest.setDateOfBirth("1984-12-11");
        userRegistrationRequest.setSsn("432134");

        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegistrationRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.errors[0]",
                                is("username must be alphanumerical and has no spaces,username must be between(2-40) chars")));

    }

    @Test
    void whenRegisterUserWithInvalidPasswordThenFailAndStatusCode400() throws Exception {
        final UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setUsername("mohannad");
        userRegistrationRequest.setPassword("aa");
        userRegistrationRequest.setDateOfBirth("1996-02-20");
        userRegistrationRequest.setSsn("432134");

        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegistrationRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]",
                                is("password must be at least four characters, at least one lower case character" +
                                        ",at least one upper case character, at least one number")));
    }

    @Test
    void whenRegisterUserWithInvalidDateOfBirthThenFailAndStatusCode400() throws Exception {
        final UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setUsername("mohannad");
        userRegistrationRequest.setPassword("abcdAa1");
        userRegistrationRequest.setDateOfBirth("1996-30-20");
        userRegistrationRequest.setSsn("432134");

        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegistrationRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]",
                        is("dateOfBirth must be in (ISO 8601) format")));

    }

    @Test
    void whenRegisterUserWithNullValuesThenFailAndStatusCode400() throws Exception {
        final UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setUsername(null);
        userRegistrationRequest.setPassword(null);
        userRegistrationRequest.setDateOfBirth(null);
        userRegistrationRequest.setSsn(null);

        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegistrationRequest)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void whenRegisterAnewUserThenSuccessAndStatusCode200() throws Exception {
        final UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setUsername("fdsadfdsadfddsadfdsadf");
        userRegistrationRequest.setPassword("Aaa1");
        userRegistrationRequest.setDateOfBirth("1984-12-11");
        userRegistrationRequest.setSsn("432134");

        given(registrationService.register(userRegistrationRequest)).willReturn(any());

        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegistrationRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenRegisterUserAlreadyExistsFailAndStatusCode409() throws Exception {
        final UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setUsername("exist");
        userRegistrationRequest.setPassword("abcdAa1");
        userRegistrationRequest.setDateOfBirth("1984-12-11");
        userRegistrationRequest.setSsn("432134");

        given(registrationService.register(userRegistrationRequest))
                .willThrow(new UserAlreadyExistsException("User already exists"));

        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegistrationRequest)))
                .andExpect(status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", is("User already exists")));
    }



    @Test
    void whenRegisterBlockedUserFailAndStatusCode403() throws Exception {
        final UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setUsername("blocked");
        userRegistrationRequest.setPassword("abcdAa1");
        userRegistrationRequest.setDateOfBirth("1815-12-10");
        userRegistrationRequest.setSsn("85385075");

        given(registrationService.register(userRegistrationRequest))
                .willThrow(new UserBlockedException("User is blocked"));

        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegistrationRequest)))
                .andExpect(status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", is("User is blocked")));
    }

}
