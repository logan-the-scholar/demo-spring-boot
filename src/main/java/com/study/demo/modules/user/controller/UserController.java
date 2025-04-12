package com.study.demo.modules.user.controller;

import com.study.demo.modules.user.dto.RegisterUserDto;
import com.study.demo.modules.user.dto.UserRecurrence;
import com.study.demo.modules.user.mapper.UserLoginResponseMapper;
import com.study.demo.modules.user.service.UserService;
import com.study.demo.modules.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController()
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserModel user) {
        return userService.login(user);
    }
    
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto user) {
        URI location = userService.register(user);
        return ResponseEntity.created(location).build();
    }

    @PostMapping("github/sign-in")
    public ResponseEntity<?> githubSignIn(@RequestHeader("X-Code") String code) {
        try {
            UserLoginResponseMapper response = userService.githubSignIn(code);

            return ResponseEntity.status(response.recurrence() == UserRecurrence.FIRST_TIME ? 201 : 200).body(response);
            //URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/by-id/{id}").buildAndExpand(response).toUri();
        } catch (Throwable error) {
            return ResponseEntity.status(500).body(error.getMessage());
        }
    }

}
