package com.study.demo.modules.file.service;

import com.study.demo.modules.file.model.File;
import com.study.demo.modules.file.model.FileResponseMapper;
import com.study.demo.modules.file.model.FileCreationDto;
import com.study.demo.modules.file.model.FileEditionDto;

import java.util.UUID;

public interface FileService {
    FileResponseMapper createFile(UUID projectId, FileCreationDto file);
    FileResponseMapper update(UUID projectId, FileEditionDto file);
    void delete(UUID id);
    File findById(UUID uuid);
    //FileResponseMapper updateUnversioned(UUID fileId, UUID projectId, FileEditionDto pFile);
}
