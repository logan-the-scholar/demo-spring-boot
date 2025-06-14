package com.study.demo.modules.project;

import com.study.demo.modules.project.model.ProjectCreationDto;
import com.study.demo.modules.project.model.ProjectModel;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

public interface ProjectService {

    List<ProjectModel> getAll();
    //ResponseEntity<?> getByOwner(String owner);
    //URI create(ProjectModel property);
    //ResponseEntity<?> codeDemo(DemoSession scriptDemo);
    //ResponseEntity<?> delete(String id);

    void createProject(ProjectCreationDto project);
    //ResponseEntity<?> modify(String id, SessionModel property);
}
