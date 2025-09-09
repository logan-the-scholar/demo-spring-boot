package com.study.demo.modules.token.service;

import com.study.demo.modules.github.mapper.AccessTokenResponse;
import com.study.demo.modules.github.mapper.GithubUserResponse;
import com.study.demo.modules.user.model.User;

public interface UserTokenService {
   GithubUserResponse getUserData(String token);
   AccessTokenResponse getAccessToken(String code);
   void create(AccessTokenResponse accessTokenSet, User user);
   void updateOrCreate(AccessTokenResponse accessTokenSet, User user);
   String getOrRefreshToken(User user);
}
