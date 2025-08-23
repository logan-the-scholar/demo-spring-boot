package com.study.demo.modules.project;

import com.study.demo.modules.file.model.FileEditionDto;
import com.study.demo.modules.project.model.ProjectCreationDto;
import com.study.demo.modules.project.service.ProjectService;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController()
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private final ProjectService projectService;

    @Qualifier("localValidatorFactoryBean")
    private final Validator validator;

    public ProjectController(ProjectService projectService, Validator validator) {
        this.projectService = projectService;
        this.validator = validator;
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<?> getAll(@PathVariable String id) {
        try {
            return ResponseEntity.ok(projectService.findAllById(UUID.fromString(id)));
        } catch (Throwable e) {
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        try {
            return ResponseEntity.ok(projectService.get(UUID.fromString(id)));
        } catch (Throwable e) {
            System.err.println(e);
            //TODO refactorize all of these exceptions
            return ResponseEntity.status(400).body(e);

        }
    }

    @MessageMapping("/{id}/session")
    @SendTo("/topic/project/{id}")
    public FileEditionDto broadcastEditCode(@DestinationVariable String id, @RequestBody @Valid FileEditionDto editedFile) {
        return editedFile;
    }

    //@CrossOrigin(exposedHeaders = "Location")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody @Valid ProjectCreationDto newProject) {
        try {
            String name = projectService.create(newProject);
            return ResponseEntity.ok().body(Map.of("name", name, "id", newProject.getWorkspaceId()));
        } catch (Throwable e) {
            return ResponseEntity.status(400).body(e.getMessage());

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(projectService.delete(UUID.fromString(id)));
        } catch (Throwable e) {
            return ResponseEntity.status(400).body(e.getMessage());

        }
    }
}
