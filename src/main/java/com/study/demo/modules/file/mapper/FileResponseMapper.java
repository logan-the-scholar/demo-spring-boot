package com.study.demo.modules.file.mapper;

import com.study.demo.modules.file.model.FileModel;

import java.util.List;
import java.util.UUID;

public record FileResponseMapper(UUID id, String name, String author, List<UUID> path, List<String> pathNames, UUID parent, String extension, String content) {
    public static FileResponseMapper fromEntity(FileModel file) {
        List<FileModel> path = file.getPath();
        return new FileResponseMapper(
                file.getId(),
                file.getName(),
                file.getAuthor().getName(),
                path.isEmpty() ? null : path.stream().map(FileModel::getId).toList(),
                path.isEmpty() ? null : path.stream().map(FileModel::getName).toList(),
                path.isEmpty() ? null : path.getLast().getId(),
                file.getExtension(),
                file.getContent()
        );
    }
}
