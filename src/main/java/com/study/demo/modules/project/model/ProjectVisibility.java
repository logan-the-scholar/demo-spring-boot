package com.study.demo.modules.project.model;

public enum ProjectVisibility {
    PUBLIC("public"),
    PRIVATE("private");

    public final String value;

    ProjectVisibility(String value) {
        this.value = value;
    }
}
