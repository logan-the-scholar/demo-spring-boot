package com.study.demo.modules.file.service;

import com.study.demo.common.exception.classes.ResourceNotFoundException;
import com.study.demo.modules.branch.model.Branch;
import com.study.demo.modules.branch.service.BranchService;
import com.study.demo.modules.commit.model.Commit;
import com.study.demo.modules.file.model.*;
import com.study.demo.modules.file.repository.FileRepository;
import com.study.demo.modules.project.service.ProjectService;
import com.study.demo.modules.project.model.Project;
import com.study.demo.modules.user.model.User;
import com.study.demo.modules.user.service.UserService;
import org.apache.coyote.BadRequestException;
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

            //!!!!!!!!!!!!!!! TODO verificacion de branch aqui y autenticacion de branch drafteada!!!!!!!!!!!!!!!!!!!!!!!!

            File createdFile = new File();
            createdFile.setProject(project);
            createdFile.setAuthor(author);
            createdFile.setCommit(branch.getDraftCommit());

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

            return FileResponseMapper.fromEntity(fileVersionService.update(branch.getDraftCommit(), pFile), branch.getId());

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(FileDeletionDto bodyFile) {
        FileVersion fileVersion = fileVersionService.findById(bodyFile.getFileId());
        //List<FileVersion> parentFiles = fileVersionService.findByFile(fileVersion.getFile());
        Commit commit = fileVersion.getFile().getCommit();

        if(commit.getStatus().equals("drafted")) {
            fileVersionService.delete(fileVersion);

            if (bodyFile.getCommitId().equals(commit.getId())) {
                repository.delete(fileVersion.getFile());

            } else {
                throw new RuntimeException("File does not belong to this commit");

            }
        } else {
            throw new RuntimeException("Intended commit cannot be modified");

        }
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
