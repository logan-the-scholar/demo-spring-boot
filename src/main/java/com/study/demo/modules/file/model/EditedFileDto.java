package com.study.demo.modules.file.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class EditedFileDto {

    @NotNull(message = "File id is required")
    private UUID id;

    private String content;

    @NotBlank(message = "The modification must have an author")
    private String author;

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

    public @NotBlank(message = "The modification must have an author") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank(message = "The modification must have an author") String author) {
        this.author = author;
    }
}
