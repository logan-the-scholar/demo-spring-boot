package com.study.demo.modules.user.service;

import com.study.demo.common.github.GithubAuthService;
import com.study.demo.common.github.UserDataResponse;
import com.study.demo.modules.user.dto.GithubSignInResponseDto;
import com.study.demo.modules.user.dto.RegisterUserDto;
import com.study.demo.modules.user.dto.UserRecurrence;
import com.study.demo.modules.user.dto.UserType;
import com.study.demo.modules.user.mapper.UserLoginResponseMapper;
import com.study.demo.modules.user.model.UserModel;
import com.study.demo.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

//    @PostConstruct
//    public void init() {
//        System.out.println("UserServiceImpl cargado con UserRepository: " + this.repository);
//    }

    public ResponseEntity<?> login(UserModel user) {
        repository.findById(user.getId());
        return null;
    }

    @Override
    public URI register(RegisterUserDto user) {
        //TODO cambiar esto, quitar el register del path /register/by-id
        UserModel created_user = new UserModel();

        created_user.setName(user.getName());
        created_user.setEmail(user.getEmail());
        created_user.setPassword(user.getPassword());
        created_user.setProfileImage(user.getProfileImage());
        //UserModel created_user = (UserModel) repository.save(user);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/by-id/{id}")
                .buildAndExpand(created_user.getId())
                .toUri();
    }

    public UserLoginResponseMapper githubSignIn(String code) {
        UserDataResponse userData = githubAuthService.getUserData(code);

        List<UserModel> foundUser = repository.findBySub("github|" + userData.getId());
        if (foundUser.isEmpty()) {
            UserModel createdUser = new UserModel();
            createdUser.setName(userData.getNickname());
            createdUser.setEmail(userData.getEmail());
            createdUser.setSub("github|" + userData.getId());
            createdUser.setUserType(UserType.GITHUB);

            repository.save(createdUser);

            return UserLoginResponseMapper.fromEntity(createdUser, UserRecurrence.FIRST_TIME);
        } else if (foundUser.size() == 1 && userData.getEmail().equalsIgnoreCase(foundUser.getFirst().getEmail())) {

            return UserLoginResponseMapper.fromEntity(foundUser.getFirst(), UserRecurrence.WELCOME_BACK);
        } else {
            throw new RuntimeException("Something weird happened here");
        }
    }
}
