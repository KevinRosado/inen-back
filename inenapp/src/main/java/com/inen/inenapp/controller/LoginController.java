package com.inen.inenapp.controller;

import com.inen.inenapp.dto.login.LoginRequest;
import com.inen.inenapp.dto.login.LoginResponse;
import com.inen.inenapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods =  {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping(value="/worker",consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf-8")
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest){
        return userService.loginUser(loginRequest);
    }
}
