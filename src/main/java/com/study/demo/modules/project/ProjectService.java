package com.study.demo.modules.project;

import com.study.demo.modules.project.mapper.ProjectResponseMapper;
import com.study.demo.modules.project.model.ProjectCreationDto;
import org.apache.coyote.BadRequestException;

import java.net.URI;
import java.util.List;
import java.util.UUID;

public interface ProjectService {
    List<ProjectResponseMapper> findAllById(UUID workspaceId) throws BadRequestException;
    URI create(ProjectCreationDto project);
    ProjectResponseMapper findById(UUID projectId) throws BadRequestException;
}
