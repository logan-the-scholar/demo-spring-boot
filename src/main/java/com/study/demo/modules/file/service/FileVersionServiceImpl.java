package com.study.demo.modules.file.service;

import com.study.demo.common.exception.classes.ResourceNotFoundException;
import com.study.demo.modules.commit.model.Commit;
import com.study.demo.modules.file.model.File;
import com.study.demo.modules.file.model.FileCreationDto;
import com.study.demo.modules.file.model.FileEditionDto;
import com.study.demo.modules.file.model.FileVersion;
import com.study.demo.modules.file.repository.FileVersionRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileVersionServiceImpl implements FileVersionService {

    @Autowired
    private final FileVersionRepository repository;

    public FileVersionServiceImpl(FileVersionRepository repository) {
        this.repository = repository;
    }

    public FileVersion create(File file, Commit commit, FileCreationDto fileDto) throws BadRequestException {

        String formatedPath = this.formatPath(fileDto.getPath(), commit.getId());

        if (this.repository.existsByPath(formatedPath)) {
            throw new BadRequestException("This file already exists in this branch");
        }

        FileVersion fileVersion = new FileVersion();
        fileVersion.setFile(file);
        fileVersion.setAuthor(file.getAuthor());
        fileVersion.setCommit(commit);
        fileVersion.setName(fileDto.getName());
        //TODO crear un validador de extensiones soportadas
        fileVersion.setExtension(fileDto.getExtension());
        fileVersion.setPath(formatedPath);
        fileVersion.setCreatedAt(fileVersion.getCreatedAt());

//        assert fileDto.getPath() != null;
//        if (!fileDto.getPath().isEmpty()) {
//            repository.findAllById(fileDto.getPath()).forEach((f) -> fileVersion.getFullPath().add(f.getId()));
//            fileVersion.setParent(fileVersion.getFullPath().getLast());
//        }

        assert fileDto.getContent() != null;
        if (fileDto.getContent().isEmpty()) {
            fileVersion.setContent(fileDto.getContent());
        }

        repository.save(fileVersion);
        return fileVersion;
    }

    public FileVersion update(Commit draftCommit, FileEditionDto pFile) throws BadRequestException {
        FileVersion file = this.findById(pFile.getId());
        String path = this.formatPath(pFile.getPath(), draftCommit.getId());

        if (!repository.existsByPath(path)) {
            FileCreationDto fileToCreate = new FileCreationDto();

            fileToCreate.setName(pFile.getNewName() != null ? pFile.getNewName() : file.getName());
            fileToCreate.setContent(pFile.getContent() != null ? pFile.getContent() : file.getContent());
            fileToCreate.setPath(pFile.getNewPath() != null ? pFile.getNewPath() : pFile.getPath());
            fileToCreate.setBranch(draftCommit.getBranch().getName());
            fileToCreate.setCreatedAt(pFile.getCreatedAt());
            fileToCreate.setExtension(pFile.getNewExtension() != null ? pFile.getNewExtension() : file.getExtension());
            fileToCreate.setAuthor(pFile.getAuthor());
            return this.create(file.getFile(), draftCommit, fileToCreate);
        }

        assert pFile.getNewPath() != null;
        if (!pFile.getNewPath().isEmpty()) {
            String newPath = this.formatPath(pFile.getNewPath(), draftCommit.getId());
            if (repository.existsByPath(newPath)) {
                throw new BadRequestException("The new path can't be a existing one");
            }

            file.setMovedFrom(path);
            file.setPath(newPath);
        }

        assert pFile.getNewName() != null;
        if (!pFile.getNewName().isEmpty()) {
            file.setName(pFile.getNewName());
        }

        assert pFile.getNewExtension() != null;
        if (!pFile.getNewExtension().isEmpty()) {
            file.setExtension(pFile.getNewExtension());
        }

        assert pFile.getContent() != null;
        if (!pFile.getContent().isBlank()) {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] decodedContent = decoder.decode(pFile.getContent());
            file.setContent(new String(decodedContent, StandardCharsets.UTF_8));
        }

        return repository.save(file);
    }

    public boolean existsByPath(String path) {
        return this.repository.existsByPath(path);
    }

    public boolean existsByPath(List<String> path, UUID commitId) throws BadRequestException {
        String formatedPath = this.formatPath(path, commitId);
        return this.existsByPath(formatedPath);
    }

    public FileVersion findById(UUID id) {
        Optional<FileVersion> file = repository.findById(id);

        if (file.isPresent()) {
            return file.get();
        } else {
            throw new ResourceNotFoundException("File not found");
        }
    }

    public Optional<FileVersion> findByFile(File originalFile) {
        return repository.findByFile(originalFile);
    }

    public String formatPath(List<String> path, UUID commitId) throws BadRequestException {
        String root = path.getFirst();
        if (root.length() > 1 || !root.startsWith(":")) {
            throw new BadRequestException(root + " is not a valid root");
        }

        path.removeFirst();
        boolean flag = path.stream().allMatch((s) -> s.startsWith("/") && s.length() > 1);
        if (!flag) {
            throw new BadRequestException(path.toString() + " is not a valid path");
        }

        return commitId.toString() + ":" + path.stream().reduce(":", String::concat);
    }
}
