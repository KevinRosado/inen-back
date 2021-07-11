package com.inen.inenapp.service;

import com.inen.inenapp.dto.login.LoginRequest;
import com.inen.inenapp.dto.login.LoginResponse;



public interface UserService {
    
    LoginResponse loginUser(LoginRequest loginRequest);
}
