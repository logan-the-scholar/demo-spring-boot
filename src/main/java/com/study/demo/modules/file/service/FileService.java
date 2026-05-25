package com.study.demo.modules.file.service;

import com.study.demo.modules.file.model.*;

import java.util.UUID;

public interface FileService {
    FileResponseMapper createFile(UUID projectId, FileCreationDto file);
    FileResponseMapper update(UUID projectId, FileEditionDto file);
    void delete(FileDeletionDto bodyFile);
    File findById(UUID uuid);
    //FileResponseMapper updateUnversioned(UUID fileId, UUID projectId, FileEditionDto pFile);
}
