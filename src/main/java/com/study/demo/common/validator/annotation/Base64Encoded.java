package com.study.demo.common.validator.annotation;

import com.study.demo.common.validator.Base64Validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Base64Validator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Base64Encoded {
    String message() default "Field must be Base64 encoded";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
