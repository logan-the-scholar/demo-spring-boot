package com.study.demo.common.github;

import com.study.demo.config.GithubAuthConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

//@Service
@Component("githubAuthService")
@EnableConfigurationProperties(GithubAuthConfigProperties.class)
public class GithubAuthService {

    private final WebClient webClient;
    GithubAuthConfigProperties githubProperties;

    public GithubAuthService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String getAccessToken(String code) {
        AccessTokenResponse response = webClient.post().uri("/login/oauth/access_token")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(Map.of(
                        "code", code
                )).retrieve()
                .bodyToMono(AccessTokenResponse.class)
                .block();

        assert response != null;
        if(response.getAccessToken() == null) {
            throw new RuntimeException("Authentication failed: " + response.getErrorDescription());
        }

        System.out.println(response);

        return response.getAccessToken();
    }

    public UserDataResponse getUserData(String code) {
        String token = getAccessToken(code);
        WebClient.ResponseSpec response = WebClient.create("https://api.github.com")
                .get().uri("/user")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .header(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .retrieve();

        return response.bodyToMono(UserDataResponse.class).block();
    }
}
