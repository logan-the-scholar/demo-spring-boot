package com.study.demo.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> register(@RequestBody UserModel user) {
        URI location = userService.register(user);
        return ResponseEntity.created(location).build();
    }
}
