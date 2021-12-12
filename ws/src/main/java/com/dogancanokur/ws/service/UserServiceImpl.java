package com.dogancanokur.ws.service;

import com.dogancanokur.ws.entity.User;
import com.dogancanokur.ws.model.input.UserInput;
import com.dogancanokur.ws.model.output.UserOutput;
import com.dogancanokur.ws.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserOutput createUser(UserInput userInput) {
        // encode password
        userInput.setPassword(passwordEncoder.encode(userInput.getPassword()));

        User user = modelMapper.map(userInput, User.class);
        User createdUser = userRepository.save(user);
        return modelMapper.map(createdUser, UserOutput.class);
    }
}
