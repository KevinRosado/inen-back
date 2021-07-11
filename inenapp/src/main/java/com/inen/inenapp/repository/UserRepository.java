package com.inen.inenapp.repository;

import com.inen.inenapp.dto.login.LoginRequest;
import com.inen.inenapp.dto.login.LoginResponse;

public interface UserRepository {
    Boolean registerUser();
    LoginResponse loginUser(LoginRequest loginRequest);
}
