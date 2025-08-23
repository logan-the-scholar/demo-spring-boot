package com.study.demo.common.validator.annotation;

import com.study.demo.common.validator.IsPathFormatValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsPathFormatValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsPathFormat {
    String message() default "Each path must be formated like the following [':','/root','/app']";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
