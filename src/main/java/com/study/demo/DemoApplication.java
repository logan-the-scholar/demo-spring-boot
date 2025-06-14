package com.study.demo;

import com.study.demo.modules.workspace.repository.WorkspaceRepository;
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
        "com.study.demo.modules.file"
})
//@EnableJpaRepositories(basePackages = {
//		"com.study.demo.modules.user.repository",
//        "com.study.demo.modules.workspace.repository"
//})
//@EntityScan(basePackages = "com.study.demo.modules")
@EnableJpaRepositories(basePackages = "com.study.demo.modules")
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("Application Running in port 8080");
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
