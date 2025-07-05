package com.study.demo.modules.file;

import com.study.demo.common.exception.classes.NotFoundException;
import com.study.demo.modules.file.mapper.FileResponseMapper;
import com.study.demo.modules.file.model.FileCreationDto;
import com.study.demo.modules.file.model.FileEditionDto;
import com.study.demo.modules.file.model.FileModel;
import com.study.demo.modules.file.repository.FileRepository;
import com.study.demo.modules.project.ProjectService;
import com.study.demo.modules.project.model.ProjectModel;
import com.study.demo.modules.user.model.UserModel;
import com.study.demo.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private final FileRepository repository;
    private final ProjectService projectService;
    private final UserService userService;

    public FileServiceImpl(FileRepository repository, ProjectService projectService, UserService userService) {
        this.repository = repository;
        this.projectService = projectService;
        this.userService = userService;
    }

    public FileResponseMapper createFile(UUID uuid, FileCreationDto file) {
        try {
            ProjectModel project = projectService.findById(uuid);
            UserModel author = userService.getUserById(UUID.fromString(file.getAuthor()));

            FileModel createdFile = new FileModel();
            createdFile.setProject(project);
            createdFile.setExtension(file.getExtension());
            createdFile.setName(file.getName());
            createdFile.setAuthor(author);

            createdFile.setChildFiles(new ArrayList<>());
            //List<FileModel> filePath = new ArrayList<>();
            if (!file.getPath().isEmpty()) {
                file.getPath().forEach((f) -> {
                    FileModel fd = this.findById(f);
                    fd.getChildFiles().add(createdFile);
                    createdFile.getPath().addFirst(fd);
                });

            } else {
                createdFile.setPath(new ArrayList<>());
            }

            FileModel savedFile = this.repository.save(createdFile);

            return FileResponseMapper.fromEntity(savedFile);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public FileResponseMapper update(UUID uuid, FileEditionDto pFile) {
        try {
            FileModel file = this.findById(uuid);

            return FileResponseMapper.fromEntity(file);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(UUID id) {

    }

    public FileModel findById(UUID uuid) {
        Optional<FileModel> file = repository.findById(uuid);

        if (file.isPresent()) {
            return file.get();
        } else {
            throw new NotFoundException("File not found");
        }
    }
}
