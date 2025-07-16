package com.study.demo.modules.file.model;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class FileEditionDto extends FileCreationDto {

    @NotNull(message = "File id is required")
    private UUID id;

    private String content;

    public @NotNull(message = "File id is required") UUID getId() {
        return id;
    }

    public void setId(@NotNull(message = "File id is required") UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
