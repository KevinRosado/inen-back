package com.inen.inenapp.service.impl;

import com.inen.inenapp.dto.login.LoginRequest;
import com.inen.inenapp.dto.login.LoginResponse;
import com.inen.inenapp.repository.UserRepository;
import com.inen.inenapp.repository.impl.UserRepositoryImpl;
import com.inen.inenapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        LoginResponse response = new LoginResponse();
        response = userRepository.loginUser(loginRequest);
        return response ;
    }

}
