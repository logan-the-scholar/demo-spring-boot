package com.study.demo.modules.token.service;

import com.study.demo.common.TokenCryptoService;
import com.study.demo.modules.github.mapper.AccessTokenResponse;
import com.study.demo.modules.github.GithubAuthService;
import com.study.demo.modules.github.mapper.GithubUserResponse;
import com.study.demo.modules.token.model.UserTokenModel;
import com.study.demo.modules.token.repository.UserTokenRepository;
import com.study.demo.modules.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private final UserTokenRepository repository;

    @Autowired
    @Qualifier("tokenCryptoService")
    private final TokenCryptoService tokenCryptoService;

    @Autowired
    @Qualifier("githubAuthService")
    private final GithubAuthService githubAuthService;

    public UserTokenServiceImpl(UserTokenRepository repository, TokenCryptoService tokenCryptoService, GithubAuthService githubAuthService) {
        this.repository = repository;
        this.tokenCryptoService = tokenCryptoService;
        this.githubAuthService = githubAuthService;
    }

    public AccessTokenResponse getAccessToken(String code) {
        return githubAuthService.requestAccessToken(code);
    }

    public GithubUserResponse getUserData(String token) {
        return githubAuthService.requestUserData(token);
    }

    public void create(AccessTokenResponse accessTokenSet, User user) {
        UserTokenModel userToken = new UserTokenModel();

        try {
            userToken.setAccess(tokenCryptoService.encrypt(accessTokenSet.getAccessToken(), user.getSub()));
            userToken.setRefresh(tokenCryptoService.encrypt(accessTokenSet.getRefreshToken(), user.getSub()));
            userToken.setUser(user);

            userToken.setExpiresIn(Instant.now().toEpochMilli() + ((accessTokenSet.getExpiresIn() - 60) * 1000L));
            userToken.setRefreshExpiresIn(Instant.now().toEpochMilli() + ((accessTokenSet.getRefreshTokenExpiresIn() - 60) * 1000L));

            this.repository.save(userToken);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOrCreate(AccessTokenResponse accessTokenSet, User user) {
        Optional<UserTokenModel> tokens = this.repository.findByUser(user);

        tokens.ifPresentOrElse((present) -> {
            try {
                present.setAccess(tokenCryptoService.encrypt(accessTokenSet.getAccessToken(), user.getSub()));
                present.setRefresh(tokenCryptoService.encrypt(accessTokenSet.getRefreshToken(), user.getSub()));

                long now = Instant.now().toEpochMilli();
                present.setExpiresIn(now + ((accessTokenSet.getExpiresIn() - 60) * 1000L));
                present.setRefreshExpiresIn(now + ((accessTokenSet.getExpiresIn() - 60) * 1000L));

                this.repository.save(present);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }, () ->
            create(accessTokenSet, user)
        );
//            if (tokens) {
//
//            } else {

//                tokens.setAccess(tokenCryptoService.encrypt(accessTokenSet.getAccessToken(), user.getSub()));
//                tokens.setRefresh(tokenCryptoService.encrypt(accessTokenSet.getRefreshToken(), user.getSub()));
//
//                long now = Instant.now().toEpochMilli();
//                tokens.setExpiresIn(now + ((accessTokenSet.getExpiresIn() - 60) * 1000L));
//                tokens.setRefreshExpiresIn(now + ((accessTokenSet.getExpiresIn() - 60) * 1000L));
//
//                this.repository.save(tokens);

    }

    public String getOrRefreshToken(User user) {
        UserTokenModel tokens = this.repository.findByUser(user).orElseThrow(() -> new NoSuchElementException("No tokens found for this user"));

        try {
            if (tokens.getExpiresIn() >= Instant.now().toEpochMilli()) {
                String refreshToken = tokenCryptoService.decrypt(tokens.getRefresh(), user.getSub());
                System.out.println("_______________________________");
                System.out.println(refreshToken);
                AccessTokenResponse newTokens = githubAuthService.requestRefreshToken(refreshToken);

                tokens.setAccess(tokenCryptoService.encrypt(newTokens.getAccessToken(), user.getSub()));
                tokens.setRefresh(tokenCryptoService.encrypt(newTokens.getRefreshToken(), user.getSub()));

                long now = Instant.now().toEpochMilli();
                tokens.setExpiresIn(now + ((newTokens.getExpiresIn() - 60) * 1000L));
                tokens.setRefreshExpiresIn(now + ((newTokens.getRefreshTokenExpiresIn() - 60) * 1000L));

                this.repository.save(tokens);
            }

            return tokenCryptoService.decrypt(tokens.getAccess(), user.getSub());

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
