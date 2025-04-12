package com.study.demo.modules.user.dto;

import jakarta.validation.constraints.*;

public class RegisterUserDto extends LoginUserDto {
    @NotBlank(message = "Name is required")
    private String name;

    private String profileImage;

    @NotBlank(message = "Password confirmation is required")
    private String confirmPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profile_image) {
        this.profileImage = profile_image;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
