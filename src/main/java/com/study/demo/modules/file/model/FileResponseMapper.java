package com.study.demo.modules.file.model;

import java.util.List;
import java.util.UUID;

public record FileResponseMapper(UUID id, String name, String author, List<UUID> path, UUID parent, String extension, String content) {
    public static FileResponseMapper fromEntity(FileModel file) {
        return new FileResponseMapper(
                file.getId(),
                file.getName(),
                file.getAuthor().getName(),
                file.getFullPath().isEmpty() ? null : file.getFullPath(),
                file.getParent().isEmpty() ? null : file.getParent().get().getId(),
                file.getExtension(),
                file.getContent()
        );
    }
}
