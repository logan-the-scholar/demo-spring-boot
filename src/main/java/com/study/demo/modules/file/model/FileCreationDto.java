package com.study.demo.modules.file.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public class FileCreationDto {

    @NotBlank(message = "File name can't be empty")
    private String name;

    @NotBlank(message = "The modification must have an author")
    private UUID author;

    private String extension;

//    @NotNull
//    private boolean isFolder;

    //@Nullable
    private List<String> path;

    @NotBlank
    @Size(min = 4, max = 20, message = "Branch name must be between 4 - 20 characters")
    private String branch;

    @Nullable
    private String content;

    @NotNull
    private Long createdAt;

    public @NotBlank(message = "File name can't be empty") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "File name can't be empty") String name) {
        this.name = name;
    }

    public @NotBlank(message = "The modification must have an author") UUID getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank(message = "The modification must have an author") UUID author) {
        this.author = author;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

//    @NotNull
//    public boolean isFolder() {
//        return isFolder;
//    }
//
//    public void setFolder(@NotNull boolean folder) {
//        isFolder = folder;
//    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public @NotBlank @Size(min = 4, max = 20, message = "Branch name must be between 4 - 20 characters") String getBranch() {
        return branch;
    }

    public void setBranch(@NotBlank @Size(min = 4, max = 20, message = "Branch name must be between 4 - 20 characters") String branch) {
        this.branch = branch;
    }

    @Nullable
    public String getContent() {
        return content;
    }

    public void setContent(@Nullable String content) {
        this.content = content;
    }

    public @NotNull Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NotNull Long createdAt) {
        this.createdAt = createdAt;
    }
}
