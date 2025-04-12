package com.study.demo.modules.user.service;

import com.study.demo.modules.user.dto.RegisterUserDto;
import com.study.demo.modules.user.mapper.UserLoginResponseMapper;
import com.study.demo.modules.user.model.UserModel;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public interface UserService {
    ResponseEntity<?> login(UserModel user);

    URI register(RegisterUserDto user);

    UserLoginResponseMapper githubSignIn(String code);
}
