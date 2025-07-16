package com.study.demo.common.github;

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

    public String getAccessToken(String code) {
        AccessTokenResponse response = webClient.post().uri("/login/oauth/access_token")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(Map.of(
                        "client_id", githubProperties.getClientId(),
                        "client_secret", githubProperties.getClientSecret(),
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

    public GithubUserResponse getUserData(String code) {
        String token = getAccessToken(code);
        WebClient.ResponseSpec response = WebClient.create("https://api.github.com")
                .get().uri("/user")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .header(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .retrieve();

        GithubUserResponse githubUser = response.bodyToMono(GithubUserResponse.class).block();
        System.out.println(githubUser);

        return githubUser;
    }
}
