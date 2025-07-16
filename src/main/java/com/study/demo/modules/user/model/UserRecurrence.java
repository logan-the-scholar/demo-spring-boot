package com.study.demo.modules.user.model;

public enum UserRecurrence {
    FIRST_TIME("first_time"),
    WELCOME_BACK("welcome_back");

    public final String value;

    UserRecurrence(String recurrence) {
        this.value = recurrence;
    }
}
