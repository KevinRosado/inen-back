package com.inen.inenapp.dto.login;
import lombok.Data;

@Data
public class LoginRequest{
    private String userCode;
    private String userPassword;
}
