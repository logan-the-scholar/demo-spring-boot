package com.study.demo.modules.user.model;

import java.util.UUID;

public record UserLoginResponseMapper(UUID id, String name, String email, String profileImage, String userType,
                                      String recurrence) {

    public static UserLoginResponseMapper fromEntity(User user, UserRecurrence recurrence) {
        return new UserLoginResponseMapper(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getProfileImage(),
                user.getUserType().value,
                recurrence.value
        );
    }
}
