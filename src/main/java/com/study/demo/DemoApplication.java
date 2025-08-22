package com.study.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EntityScan(basePackages = {
		"com.study.demo.modules.user",
		"com.study.demo.modules.workspace",
        "com.study.demo.modules.project",
        "com.study.demo.modules.file",
        "com.study.demo.modules.token",
        "com.study.demo.modules.commit",
        "com.study.demo.modules.branch"
})
@EnableJpaRepositories(basePackages = "com.study.demo.modules")
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("Application Running in port 8080");

//        try {
//            TinkConfig.register();
//            KeysetHandle handle = KeysetHandle.generateNew(AeadKeyTemplates.AES256_GCM);
//            java.nio.file.Files.createDirectories(java.nio.file.Path.of("secrets"));
//            CleartextKeysetHandle.write(
//                    handle,
//                    JsonKeysetWriter.withPath(java.nio.file.Path.of("secrets/github.aead.json")));
//        } catch(Throwable e) {
//            throw new InternalException(e);
//        }
    }

    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {
            // TODO cambiar el endpoint del front a variable de entorno.

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedHeaders("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowCredentials(true);
            }
        };
    }

}
