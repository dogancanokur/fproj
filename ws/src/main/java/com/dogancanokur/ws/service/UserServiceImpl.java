package com.dogancanokur.ws.service;

import com.dogancanokur.ws.entity.User;
import com.dogancanokur.ws.model.input.UserInput;
import com.dogancanokur.ws.model.output.UserOutput;
import com.dogancanokur.ws.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserOutput createUser(UserInput userInput) {
        User user = modelMapper.map(userInput, User.class);
        User createdUser = userRepository.save(user);
        return modelMapper.map(createdUser, UserOutput.class);
    }
}
