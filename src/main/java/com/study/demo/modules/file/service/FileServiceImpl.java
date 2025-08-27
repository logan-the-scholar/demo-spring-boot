package com.study.demo.modules.file.service;

import com.study.demo.common.exception.classes.ResourceNotFoundException;
import com.study.demo.modules.branch.model.Branch;
import com.study.demo.modules.branch.service.BranchService;
import com.study.demo.modules.file.model.*;
import com.study.demo.modules.file.repository.FileRepository;
import com.study.demo.modules.project.service.ProjectService;
import com.study.demo.modules.project.model.Project;
import com.study.demo.modules.user.model.User;
import com.study.demo.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private final FileRepository repository;
    private final ProjectService projectService;
    private final UserService userService;
    private final FileVersionService fileVersionService;
    private final BranchService branchService;

    public FileServiceImpl(FileRepository repository, ProjectService projectService, UserService userService, FileVersionService fileVersionService, BranchService branchService) {
        this.repository = repository;
        this.projectService = projectService;
        this.userService = userService;
        this.fileVersionService = fileVersionService;
        this.branchService = branchService;
    }

    public FileResponseMapper createFile(UUID projectId, FileCreationDto pFile) {
        try {
            Project project = projectService.findById(projectId);
            User author = userService.getUserByName(pFile.getAuthor());

            Branch branch = branchService.findByProjectAndName(project, pFile.getBranch()).orElseThrow(() ->
                    new ResourceNotFoundException(pFile.getBranch() + " branch can't be found"));

            File createdFile = new File();
            createdFile.setProject(project);
            createdFile.setAuthor(author);

            FileVersion firstVersion = fileVersionService.create(createdFile, branch.getDraftCommit(), pFile);
            //repository.save(createdFile);

            return FileResponseMapper.fromEntity(firstVersion, branch.getDraftCommit().getId());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public FileResponseMapper update(UUID projectId, FileEditionDto pFile) {
        try {
            Project project = projectService.findById(projectId);
            User author = userService.getUserByName(pFile.getAuthor());

            Branch branch = branchService.findByProjectAndName(project, pFile.getBranch()).orElseThrow(() ->
                    new ResourceNotFoundException(pFile.getBranch() + " branch can't be found"));

//            if (!fileVersionService.existsByPath(pFile.getPath(), branch.getDraftCommit().getId())) {
//                File refFile = this.findById(pFile.getId());
//                FileCreationDto aaa = new FileCreationDto();
//
//                aaa.setExtension();
//                aaa.setName();
//                aaa.setPath();
//                aaa.setContent();
//
//                return FileResponseMapper.fromEntity(fileVersionService.create(refFile, branch.getDraftCommit(), aaa));
//
//            } else {
                return FileResponseMapper.fromEntity(fileVersionService.update(branch.getDraftCommit(), pFile), branch.getId());

            //}
//            if (pFile.getNewPath() != null) {
//
//            }
//            if (pFile.getNewName() != null) {
//                file.setName(pFile.getNewName());
//            }
//            if (pFile.getNewExtension() != null) {
//                file.setExtension(pFile.getNewExtension());
//            }
            //return FileResponseMapper.fromEntity(file);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    //TODO agregar was_moved was_deleted para poder manejarlo
//    public FileResponseMapper updateUnversioned(UUID fileId, UUID projectId, FileEditionDto pFile) {
//        try {
//            FileVersion file = fileVersionService.findById(fileId);
//
//            if (pFile.getNewPath() != null) {
//
//            }
//            if (pFile.getNewName() != null) {
//               file.setName(pFile.getName());
//            }
//
//            return FileResponseMapper.fromEntity(file);
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void delete(UUID id) {
        File file = this.findById(id);
        repository.delete(file);
    }

    public File findById(UUID uuid) {
        Optional<File> file = repository.findById(uuid);

        if (file.isPresent()) {
            return file.get();
        } else {
            throw new ResourceNotFoundException("File not found");
        }
    }
}
