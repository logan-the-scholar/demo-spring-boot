package com.study.demo.config.github;

import com.study.demo.config.WebClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GithubAuthConfig {

    private final WebClientConfig webClientConfig;

    public GithubAuthConfig(WebClientConfig webClientConfig) {
        this.webClientConfig = webClientConfig;
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        webClientConfig.log(GithubAuthConfig.class);

        return builder.baseUrl("https://github.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(webClientConfig.logRequest()).filter(webClientConfig.logResponse()).filter(webClientConfig.errorResponse())
                .build();
    }
}
