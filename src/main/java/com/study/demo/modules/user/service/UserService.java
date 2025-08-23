package com.study.demo.modules.user.service;

import com.study.demo.modules.user.model.LoginUserDto;
import com.study.demo.modules.user.model.RegisterUserDto;
import com.study.demo.modules.user.model.UserLoginResponseMapper;
import com.study.demo.modules.user.model.UserModel;
import org.apache.coyote.BadRequestException;

import java.util.UUID;

public interface UserService {
    UserLoginResponseMapper login(LoginUserDto user);

    void register(RegisterUserDto user);

    UserLoginResponseMapper githubSignIn(String code);

    UserModel getUserById(UUID uuid) throws BadRequestException;

}
