package com.study.demo.modules.project;

import com.study.demo.modules.project.mapper.ProjectResponseMapper;
import com.study.demo.modules.project.model.ProjectCreationDto;
import com.study.demo.modules.project.model.ProjectModel;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ProjectService {
    List<ProjectResponseMapper> findAllById(UUID workspaceId);
    String create(ProjectCreationDto project);
    ProjectModel findById(UUID projectId) throws BadRequestException;
    Map<String, String> delete(UUID uuid) throws BadRequestException;
}
