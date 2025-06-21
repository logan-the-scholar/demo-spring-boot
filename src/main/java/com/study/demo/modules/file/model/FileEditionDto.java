package com.study.demo.modules.file.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class FileEditionDto extends FileCreationDto {

    @NotNull(message = "File id is required")
    private UUID id;

    private String content;

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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
