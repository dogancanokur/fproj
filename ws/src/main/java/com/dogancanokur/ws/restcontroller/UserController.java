package com.dogancanokur.ws.restcontroller;

import com.dogancanokur.ws.model.input.UserInput;
import com.dogancanokur.ws.model.output.UserOutput;
import com.dogancanokur.ws.service.UserService;
import com.dogancanokur.ws.shared.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/api/1.0/users")
    @ResponseStatus(HttpStatus.CREATED) // 201
    public GenericResponse createUser(@RequestBody UserInput userInput) {
        UserOutput userOutput = userService.createUser(userInput);
        GenericResponse response = new GenericResponse();
        response.setMessage(userOutput.getUsername() + " created");
        log.info(response.getMessage());
        return response;
    }
}
