package com.study.demo.modules.file.model;

import com.study.demo.common.validator.annotation.IsPathFormat;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public class FileEditionDto {

    @NotNull(message = "File id is required (actual or previous commit file id)")
    private UUID id;

    @NotBlank(message = "The modification must have an author")
    @Size(min = 4, max = 30)
    private String author;

    @NotNull
    @IsPathFormat
    private List<String> path;

    @Nullable
    private String newName;

    @Nullable
    private String content;

    @Nullable
    private String newExtension;

    @Nullable
    @IsPathFormat
    private List<String> newPath;

    @NotNull
    private Long createdAt;

    @NotBlank
    @Size(min = 4, max = 20, message = "Branch name must be between 4 - 20 characters")
    private String branch;

    public @NotNull(message = "File id is required") UUID getId() {
        return id;
    }

    public void setId(@NotNull(message = "File id is required") UUID id) {
        this.id = id;
    }

    public @NotBlank(message = "The modification must have an author") @Size(min = 4, max = 30) String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank(message = "The modification must have an author") @Size(min = 4, max = 30) String author) {
        this.author = author;
    }

    @Nullable
    public String getNewName() {
        return newName;
    }

    public void setNewName(@Nullable String newName) {
        this.newName = newName;
    }

    @Nullable
    public String getContent() {
        return content;
    }

    public void setContent(@Nullable String content) {
        this.content = content;
    }

    @Nullable
    public String getNewExtension() {
        return newExtension;
    }

    public void setNewExtension(@Nullable String newExtension) {
        this.newExtension = newExtension;
    }

    @Nullable
    public List<String> getNewPath() {
        return newPath;
    }

    public void setNewPath(@Nullable List<String> newPath) {
        this.newPath = newPath;
    }

    public @NotBlank @Size(min = 4, max = 20, message = "Branch name must be between 4 - 20 characters") String getBranch() {
        return branch;
    }

    public void setBranch(@NotBlank @Size(min = 4, max = 20, message = "Branch name must be between 4 - 20 characters") String branch) {
        this.branch = branch;
    }

    public @NotNull List<String> getPath() {
        return path;
    }

    public void setPath(@NotNull List<String> path) {
        this.path = path;
    }

    public @NotNull Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NotNull Long createdAt) {
        this.createdAt = createdAt;
    }
}
