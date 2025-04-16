package com.study.demo.modules.user.dto;


import jakarta.validation.constraints.NotBlank;

public class RegisterUserDto extends LoginUserDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Password confirmation is required")
    private String confirmPassword;

    public @NotBlank(message = "Name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Password confirmation is required") String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(@NotBlank(message = "Password confirmation is required") String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
