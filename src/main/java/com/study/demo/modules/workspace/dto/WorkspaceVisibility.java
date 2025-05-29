package com.study.demo.modules.workspace.dto;

public enum WorkspaceVisibility {
    PUBLIC("public"),
    PRIVATE("private");

    public final String value;

    WorkspaceVisibility(String value) {
        this.value = value;
    }
}
