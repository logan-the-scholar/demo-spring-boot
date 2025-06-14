package com.study.demo.modules.user.model;

public enum UserType {
    GITHUB("github"),
    LOCAL("local"),
    GOOGLE("google");

    public final String value;

    UserType(String type) {
        this.value = type;
    }
}
