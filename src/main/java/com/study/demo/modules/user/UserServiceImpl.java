package com.study.demo.modules.user;

import com.study.demo.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class UserServiceImpl implements UserService {

    //@Autowired
    private final UserRepository repository;

//    public UserServiceImpl(UserRepository repository) {
//        this.repository = repository;
//    }

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

//    @PostConstruct
//    public void init() {
//        System.out.println("UserServiceImpl cargado con UserRepository: " + this.repository);
//    }

    public ResponseEntity<?> login(UserModel user) {
        repository.findById(user.getId());
        return null;
    }

    public URI register(UserModel user) {

        UserModel created_user = (UserModel) repository.save(user);
//TODO cambiar esto, quitar el register del path /register/by-id
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/by-id/{id}")
                .buildAndExpand(created_user.getId())
                .toUri();
    }
}
