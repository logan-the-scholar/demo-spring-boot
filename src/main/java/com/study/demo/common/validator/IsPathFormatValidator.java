package com.study.demo.common.validator;

import com.study.demo.common.validator.annotation.IsPathFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.coyote.BadRequestException;

import java.util.List;

public class IsPathFormatValidator implements ConstraintValidator<IsPathFormat, List<String>> {
    @Override
    public void initialize(IsPathFormat constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<String> strings, ConstraintValidatorContext constraintValidatorContext) {
        if (strings == null) {
            return true;
        }

        String root = strings.getFirst();
        if(root.length() > 1 || !root.startsWith(":")) {
            return false;
        }

        strings.removeFirst();
        return strings.stream().allMatch((s) -> s.startsWith("/") && s.length() > 1);
    }
}
