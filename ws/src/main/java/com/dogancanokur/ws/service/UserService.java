package com.dogancanokur.ws.service;

import com.dogancanokur.ws.model.input.UserInput;
import com.dogancanokur.ws.model.output.UserOutput;

public interface UserService {
    UserOutput createUser(UserInput userInput);
}
