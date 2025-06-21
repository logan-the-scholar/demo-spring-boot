package com.study.demo.modules.file.mapper;

import com.study.demo.modules.file.model.FileModel;

public record FileResponseMapper(java.util.UUID id, String name, String path, String extension, String content) {
    public static FileResponseMapper fromEntity(FileModel file) {
        return new FileResponseMapper(
                file.getId(),
                file.getName(),
                file.getPath(),
                file.getExtension(),
                file.getContent()
        );
    }
}
