package com.gamesys.task.controller;

import com.gamesys.task.dto.UserRegistrationRequest;
import com.gamesys.task.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Mohannad Elmagharby
 * on 5/26/2021
 */
@Slf4j
@Validated
@RequestMapping("api/users")
@RestController
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<?> Register(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest){
        log.info("register new user,{}" , userRegistrationRequest);
        return new ResponseEntity(this.registrationService.register(userRegistrationRequest), HttpStatus.CREATED);
    }

}
