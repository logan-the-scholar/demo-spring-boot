package com.study.demo.modules.github;

import com.study.demo.modules.github.mapper.AccessTokenResponse;
import com.study.demo.modules.github.mapper.GithubUserResponse;
import com.study.demo.config.GithubAuthConfigProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component("githubAuthService")
@EnableConfigurationProperties(GithubAuthConfigProperties.class)
public class GithubAuthService {

    private final WebClient webClient;
    private final GithubAuthConfigProperties githubProperties;

    public GithubAuthService(WebClient webClient, @Qualifier("githubAuthConfigProperties") GithubAuthConfigProperties githubProperties) {
        this.webClient = webClient;
        this.githubProperties = githubProperties;
    }

    public AccessTokenResponse requestAccessToken(String code) {
        AccessTokenResponse response = webClient.post().uri("/login/oauth/access_token")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(Map.of(
                        "client_id", githubProperties.getClientId(),
                        "client_secret", githubProperties.getClientSecret(),
                        "code", code
                )).retrieve().bodyToMono(AccessTokenResponse.class)
                .block();

        assert response != null;
        if(response.getAccessToken() == null) {
            throw new RuntimeException("Authentication failed: " + response.getErrorDescription());
        }

        return response;
    }

    public GithubUserResponse requestUserData(String accessToken) {
        WebClient.ResponseSpec response = WebClient.create("https://api.github.com")
                .get().uri("/user")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .retrieve();

        return response.bodyToMono(GithubUserResponse.class).block();
    }

    public AccessTokenResponse requestRefreshToken(String refreshToken) {
        AccessTokenResponse response = webClient.post().uri("login/oauth/access_token")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(Map.of(
                        "client_id", githubProperties.getClientId(),
                        "client_secret", githubProperties.getClientSecret(),
                        "grant_type", "refresh_token",
                        "refresh_token", refreshToken
                ))
                .retrieve().bodyToMono(AccessTokenResponse.class).block();

        assert response != null;
        if (response.getAccessToken() == null) {
            throw new RuntimeException("Authentication failed: " + response.getErrorDescription());
        }

        return response;
    }
}
