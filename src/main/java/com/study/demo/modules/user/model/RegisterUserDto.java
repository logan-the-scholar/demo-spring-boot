package com.study.demo.modules.user.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterUserDto extends LoginUserDto {
    @NotBlank(message = "Name is required")
    @Size(min = 4, max = 30)
    private String name;

    @NotBlank(message = "Password confirmation is required")
    private String confirmPassword;

    public @NotBlank(message = "Name is required") @Size(min = 4, max = 30) String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") @Size(min = 4, max = 30) String name) {
        this.name = name;
    }

    public @NotBlank(message = "Password confirmation is required") String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(@NotBlank(message = "Password confirmation is required") String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
