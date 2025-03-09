package com.study.demo.modules.user;

import org.springframework.http.ResponseEntity;

import java.net.URI;

public interface UserService {
    ResponseEntity<?> login(UserModel user);

    URI register(UserModel user);
}
