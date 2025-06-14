package com.study.demo.modules.user;

import com.study.demo.modules.user.model.LoginUserDto;
import com.study.demo.modules.user.model.RegisterUserDto;
import com.study.demo.modules.user.model.UserRecurrence;
import com.study.demo.modules.user.mapper.UserLoginResponseMapper;
import com.study.demo.modules.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("user")
public class UserController {

    @Autowired
    private final UserService userService;
    @Qualifier("localValidatorFactoryBean")
    private final Validator validator;

    public UserController(UserService userService, Validator validator) {
        this.userService = userService;
        this.validator = validator;
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

        } catch (Throwable error) {
            return ResponseEntity.status(400).body(error.getMessage());

        }
    }

    @PostMapping("github/sign-in")
    public ResponseEntity<?> githubSignIn(@RequestHeader("X-Code") @NotBlank(message = "Github code is required as header") @Valid String code) {
        try {
            UserLoginResponseMapper githubUser = userService.githubSignIn(code);
            return ResponseEntity.status(githubUser.recurrence().equalsIgnoreCase(UserRecurrence.FIRST_TIME.value) ? 201 : 200).body(githubUser);
            //TODO testear de nuevo el guardado de imagen de github y agregar el gravatar

        } catch (Throwable error) {
            return ResponseEntity.status(500).body(error.getMessage());

        }
    }

}
