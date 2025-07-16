package com.study.demo.modules.file.model;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class FolderReferenceDto {

    @NotNull(message = "folder reference path id must be present")
    UUID id;

    @NotNull(message = "Is temporal status must be present")
    boolean isTemp;

    public @NotNull(message = "folder reference path id must be present") UUID getId() {
        return id;
    }

    public void setId(@NotNull(message = "folder reference path id must be present") UUID id) {
        this.id = id;
    }

    @NotNull(message = "Is temporal status must be present")
    public boolean isTemp() {
        return isTemp;
    }

    public void setTemp(@NotNull(message = "Is temporal status must be present") boolean temp) {
        isTemp = temp;
    }
}
