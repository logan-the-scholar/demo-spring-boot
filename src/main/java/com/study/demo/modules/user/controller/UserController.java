package com.study.demo.modules.user.controller;

import com.study.demo.modules.user.dto.LoginUserDto;
import com.study.demo.modules.user.dto.RegisterUserDto;
import com.study.demo.modules.user.dto.UserRecurrence;
import com.study.demo.modules.user.mapper.UserLoginResponseMapper;
import com.study.demo.modules.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginUserDto loginCredentials) {
        try {
            UserLoginResponseMapper localUser = userService.login(loginCredentials);
            return ResponseEntity.status(200).body(localUser);

        } catch (Throwable error) {
            return ResponseEntity.status(400).body(error.getMessage());

        }
    }
    
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterUserDto user) {

        try {
            userService.register(user);
            return ResponseEntity.status(201).body(Map.of("message", "User created successfully"));

        } catch(Throwable error) {
            return ResponseEntity.status(400).body(error.getMessage());

        }
    }

    @PostMapping("github/sign-in")
    public ResponseEntity<?> githubSignIn(@RequestHeader("X-Code") String code) {
        try {
            UserLoginResponseMapper githubUser = userService.githubSignIn(code);
            return ResponseEntity.status(githubUser.recurrence() == UserRecurrence.FIRST_TIME ? 201 : 200).body(githubUser);

            //URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/by-id/{id}").buildAndExpand(response).toUri();
        } catch (Throwable error) {
            return ResponseEntity.status(500).body(error.getMessage());

        }
    }

}
