package com.study.demo.modules.file.service;

import com.study.demo.modules.commit.model.Commit;
import com.study.demo.modules.file.model.File;
import com.study.demo.modules.file.model.FileCreationDto;
import com.study.demo.modules.file.model.FileEditionDto;
import com.study.demo.modules.file.model.FileVersion;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileVersionService {
    FileVersion create(File file, Commit commit, FileCreationDto fileDto) throws BadRequestException;
    FileVersion findById(UUID id);
    Optional<FileVersion> findByFile(File originalFile);
    FileVersion update(Commit draftCommit, FileEditionDto pFile) throws BadRequestException;
    boolean existsByPath(String path);
    boolean existsByPath(List<String> path, UUID commitId) throws BadRequestException;
}
