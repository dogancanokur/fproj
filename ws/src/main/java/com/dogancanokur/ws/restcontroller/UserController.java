package com.dogancanokur.ws.restcontroller;

import com.dogancanokur.ws.error.ApiError;
import com.dogancanokur.ws.model.input.UserInput;
import com.dogancanokur.ws.model.output.UserOutput;
import com.dogancanokur.ws.service.UserService;
import com.dogancanokur.ws.shared.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> createUser(@RequestBody UserInput userInput) {
        if ((userInput.getUsername() == null || "".equals(userInput.getUsername())) || (userInput.getDisplayName() == null || "".equals(userInput.getDisplayName())) || (userInput.getPassword() == null || "".equals(userInput.getPassword()))) {
            Map<String, String> validationErrors = new HashMap<>();
            if (userInput.getUsername() == null || "".equals(userInput.getUsername())) {
                validationErrors.put("username", "Username cannot be empty.");
            }
            if (userInput.getDisplayName() == null || "".equals(userInput.getDisplayName())) {
                validationErrors.put("displayName", "Display Name cannot be empty.");
            }
            if (userInput.getPassword() == null || "".equals(userInput.getPassword())) {
                validationErrors.put("password", "Password cannot be empty.");
            }
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), "Username cannot be empty.", "/api/1.0/users", validationErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
        }

        UserOutput userOutput = userService.createUser(userInput);
        GenericResponse response = new GenericResponse();
        response.setMessage(userOutput.getUsername() + " created");
        log.info(response.getMessage());
        return ResponseEntity.ok(response);
    }
}
