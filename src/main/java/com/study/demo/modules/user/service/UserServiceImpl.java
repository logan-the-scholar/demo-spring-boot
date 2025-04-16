package com.study.demo.modules.user.service;

import com.study.demo.common.exception.EmailAlreadyExistsException;
import com.study.demo.common.exception.InvalidCredentialsException;
import com.study.demo.common.exception.PasswordDontMatchException;
import com.study.demo.common.github.GithubAuthService;
import com.study.demo.common.github.GithubUserResponse;
import com.study.demo.modules.user.dto.LoginUserDto;
import com.study.demo.modules.user.dto.RegisterUserDto;
import com.study.demo.modules.user.dto.UserRecurrence;
import com.study.demo.modules.user.dto.UserType;
import com.study.demo.modules.user.mapper.UserLoginResponseMapper;
import com.study.demo.modules.user.model.UserModel;
import com.study.demo.modules.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository repository;

    @Autowired
    @Qualifier("githubAuthService")
    private final GithubAuthService githubAuthService;

    public UserServiceImpl(UserRepository repository, GithubAuthService githubAuthService) {
        this.repository = repository;
        this.githubAuthService = githubAuthService;
    }

    public UserLoginResponseMapper login(LoginUserDto user) {
        List<UserModel> foundUser = repository.findByEmail(user.getEmail());

        boolean flag = foundUser.getFirst().getEmail().equalsIgnoreCase(user.getEmail())
                && foundUser.getFirst().getPassword().equalsIgnoreCase(user.getPassword());

        if (foundUser.isEmpty() || !flag) {
            throw new InvalidCredentialsException("Invalid Credentials");

        }
        return UserLoginResponseMapper.fromEntity(foundUser.getFirst(), UserRecurrence.WELCOME_BACK);

    }

    @Override
    public void register(RegisterUserDto user) {
        if (!user.getPassword().equalsIgnoreCase(user.getConfirmPassword())) {
            throw new PasswordDontMatchException("Passwords don't match!");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("This email is already on use");
        }

        UserModel createdUser = new UserModel();

        createdUser.setName(user.getName());
        createdUser.setEmail(user.getEmail());
        createdUser.setPassword(user.getPassword());
        createdUser.setUserType(UserType.LOCAL);

        repository.save(createdUser);

    }

    public UserLoginResponseMapper githubSignIn(String code) {
        GithubUserResponse githubUser = githubAuthService.getUserData(code);
        List<UserModel> foundUser = repository.findBySub("github|" + githubUser.getId());

        if (foundUser.isEmpty()) {
            if (repository.existsByEmail(githubUser.getEmail())) {
                throw new EmailAlreadyExistsException("This email is already on use");

            }

            UserModel createdUser = new UserModel();
            createdUser.setName(githubUser.getNickname());
            createdUser.setEmail(githubUser.getEmail());
            createdUser.setSub("github|" + githubUser.getId());
            createdUser.setUserType(UserType.GITHUB);

            repository.save(createdUser);

            return UserLoginResponseMapper.fromEntity(createdUser, UserRecurrence.FIRST_TIME);

        } else if (foundUser.size() == 1 && githubUser.getEmail().equalsIgnoreCase(foundUser.getFirst().getEmail())) {
            return UserLoginResponseMapper.fromEntity(foundUser.getFirst(), UserRecurrence.WELCOME_BACK);

        } else {
            throw new RuntimeException("Something weird happened here, dupped user or similar issue");

        }
    }
}
