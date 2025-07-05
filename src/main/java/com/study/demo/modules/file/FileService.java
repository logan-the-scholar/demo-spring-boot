package com.study.demo.modules.file;

import com.study.demo.modules.file.mapper.FileResponseMapper;
import com.study.demo.modules.file.model.FileCreationDto;
import com.study.demo.modules.file.model.FileEditionDto;

import java.util.UUID;

public interface FileService {
    FileResponseMapper createFile(UUID uuid, FileCreationDto file);
    FileResponseMapper update(UUID uuid, FileEditionDto file);
    void delete(UUID id);
}
