package com.study.demo.modules.user.service;

import com.study.demo.common.exception.classes.EmailAlreadyExistsException;
import com.study.demo.common.exception.classes.InvalidCredentialsException;
import com.study.demo.common.exception.classes.PasswordDontMatchException;
import com.study.demo.modules.github.mapper.AccessTokenResponse;
import com.study.demo.modules.github.GithubAuthService;
import com.study.demo.modules.github.mapper.GithubUserResponse;
import com.study.demo.modules.token.service.UserTokenService;
import com.study.demo.modules.user.model.LoginUserDto;
import com.study.demo.modules.user.model.RegisterUserDto;
import com.study.demo.modules.user.model.UserRecurrence;
import com.study.demo.modules.user.model.UserType;
import com.study.demo.modules.user.model.UserLoginResponseMapper;
import com.study.demo.modules.user.model.UserModel;
import com.study.demo.modules.user.repository.UserRepository;
import com.study.demo.modules.workspace.service.WorkspaceService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository repository;

    private final UserTokenService userTokenService;
    private WorkspaceService workspaceService;

    public UserServiceImpl(UserRepository repository, GithubAuthService githubAuthService, UserTokenService userTokenService) {
        this.repository = repository;
        this.userTokenService = userTokenService;
    }

    @Autowired
    public void setWorkspaceService(@Lazy WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    public WorkspaceService getWorkspaceService() {
        return workspaceService;
    }

    public UserLoginResponseMapper login(LoginUserDto user) {
        Optional<UserModel> foundUser = repository.findByEmail(user.getEmail());

        boolean flag = foundUser.isPresent() && foundUser.get().getEmail().equalsIgnoreCase(user.getEmail())
                && foundUser.get().getPassword().equalsIgnoreCase(user.getPassword());

        if (foundUser.isEmpty() || !flag) {
            throw new InvalidCredentialsException("Invalid Credentials");

        }

        return UserLoginResponseMapper.fromEntity(foundUser.get(), UserRecurrence.WELCOME_BACK);

    }

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
        UserModel user_ = repository.save(createdUser);

        workspaceService.createDefault(user_.getName(), user_);

    }

    //TODO deberia devolver un timestamp del tiempo de expiracion de ambos tokens (como una cookie??)
    //TODO probar si cada vez que hago signin se actualizan o permanecen iguales los tokens

    public UserLoginResponseMapper githubSignIn(String code) {
        AccessTokenResponse tokens = userTokenService.getAccessToken(code);
        GithubUserResponse githubUser = userTokenService.getUserData(tokens.getAccessToken());
        Optional<UserModel> existingUser = repository.findBySub("github|" + githubUser.getId());

        if (existingUser.isEmpty()) {
            if (repository.existsByEmail(githubUser.getEmail())) {
                throw new EmailAlreadyExistsException("This email is already on use");

            }

            UserModel createdUser = new UserModel();

            createdUser.setName(githubUser.getNickname());
            createdUser.setEmail(githubUser.getEmail());
            createdUser.setSub("github|" + githubUser.getId());
            createdUser.setUserType(UserType.GITHUB);
            createdUser.setProfileImage(githubUser.getProfileImage());
            UserModel savedUser = repository.save(createdUser);

            workspaceService.createDefault(savedUser.getName(), savedUser);

            userTokenService.create(tokens, savedUser);

            return UserLoginResponseMapper.fromEntity(createdUser, UserRecurrence.FIRST_TIME);

        } else {
            UserModel found = existingUser.get();

            if (githubUser.getEmail().equalsIgnoreCase(found.getEmail())) {
                userTokenService.updateOrCreate(tokens, found);
                return UserLoginResponseMapper.fromEntity(found, UserRecurrence.WELCOME_BACK);

            } else {
                //TODO
                throw new EmailAlreadyExistsException("registered email and github id do not match, (refactorizar)");
            }
        }
    }

    public UserModel getUserById(UUID uuid) throws BadRequestException {
        Optional<UserModel> user = repository.findById(uuid);

        if (user.isPresent()) {
            return user.get();

        } else {
            throw new BadRequestException("User not found");

        }
    }
}
