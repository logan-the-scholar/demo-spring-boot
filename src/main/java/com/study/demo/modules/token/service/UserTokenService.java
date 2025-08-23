package com.study.demo.modules.token.service;

import com.study.demo.modules.github.mapper.AccessTokenResponse;
import com.study.demo.modules.github.mapper.GithubUserResponse;
import com.study.demo.modules.user.model.UserModel;

public interface UserTokenService {
   GithubUserResponse getUserData(String token);
   AccessTokenResponse getAccessToken(String code);
   void create(AccessTokenResponse accessTokenSet, UserModel user);
   void updateOrCreate(AccessTokenResponse accessTokenSet, UserModel user);
   String getOrRefreshToken(UserModel user);
}
