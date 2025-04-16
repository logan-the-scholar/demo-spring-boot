package com.study.demo.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component("localValidatorFactoryBean")
public class ValidatorConfig {
    @Bean
    public Validator validator() {
        return Validation.byProvider( HibernateValidator.class )
                .configure()
                .showValidatedValuesInTraceLogs( true )
                .buildValidatorFactory()
                .getValidator();
    }
}
