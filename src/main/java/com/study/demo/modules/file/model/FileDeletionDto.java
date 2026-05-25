package com.study.demo.modules.file.model;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class FileDeletionDto {

    @NotNull(message = "File id is required")
    private UUID fileId;

    @NotNull(message = "File id is required")
    private UUID commitId;

    public @NotNull(message = "File id is required") UUID getFileId() {
        return fileId;
    }

    public void setFileId(@NotNull(message = "File id is required") UUID fileId) {
        this.fileId = fileId;
    }

    public @NotNull(message = "File id is required") UUID getCommitId() {
        return commitId;
    }

    public void setCommitId(@NotNull(message = "File id is required") UUID commitId) {
        this.commitId = commitId;
    }
}
