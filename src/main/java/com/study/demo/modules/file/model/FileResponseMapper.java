package com.study.demo.modules.file.model;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public record FileResponseMapper(UUID id, String name, String author, List<String> path, String extension, String content, String moved_from,
                                 UUID commitId, boolean isDrafted) {
    public static FileResponseMapper fromEntity(FileVersion file, UUID actualCommit) {

        return new FileResponseMapper(
                file.getId(),
                file.getName(),
                file.getAuthor().getName(),
                file.getPath().isEmpty() ? null : List.of(file.getPath().split("/")),
                file.getExtension(),
                file.getContent() != null ? Base64.getEncoder().encodeToString(file.getContent().getBytes(StandardCharsets.UTF_8)) : null,
                file.getMovedFrom() == null ? null : file.getMovedFrom(),
                file.getCommit().getId(),
                actualCommit.equals(file.getCommit().getId())
        );
    }
}
