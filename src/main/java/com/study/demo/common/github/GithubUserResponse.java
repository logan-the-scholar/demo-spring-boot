package com.study.demo.common.github;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubUserResponse {
    private String id;

    @JsonProperty("login")
    private String nickname;

    private String email;

    @JsonProperty("avatar_url")
    private String profileImage;
    //TODO add gravatar field here and handling in case of nullish

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
