package com.study.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

@Component("webClientConfig")
public class WebClientConfig {

    private Logger log;

    public void log(Class<?> classLog) {
        this.log = LoggerFactory.getLogger(classLog);
    }

    public ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }

    public ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.info("Response: {}", clientResponse.statusCode().value());
            return Mono.just(clientResponse);
        });
    }

    public ExchangeFilterFunction errorResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().isError()) {

                return clientResponse.bodyToMono(String.class)
                        .flatMap(body -> {
                            String error = String.format("returned status %s with body %s", clientResponse.statusCode(), body);
                            log.error(error);
                            return Mono.error(new RuntimeException(error));
                        });
            } else {
                return Mono.just(clientResponse);
            }
        });
    }
}
