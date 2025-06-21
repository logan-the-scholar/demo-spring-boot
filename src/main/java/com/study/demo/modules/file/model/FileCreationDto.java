package com.study.demo.modules.file.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FileCreationDto {

    @NotBlank(message = "File name can't be empty")
    private String name;

    @NotBlank(message = "The modification must have an author")
    private String author;

    private String extension;

    @NotNull
    private boolean isFolder;

    @NotBlank
    private String path;

    public @NotBlank String getPath() {
        return path;
    }

    public void setPath(@NotBlank String path) {
        this.path = path;
    }

    public @NotBlank(message = "File name can't be empty") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "File name can't be empty") String name) {
        this.name = name;
    }

    public @NotBlank(message = "The modification must have an author") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank(message = "The modification must have an author") String author) {
        this.author = author;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @NotNull
    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(@NotNull boolean folder) {
        isFolder = folder;
    }
}
