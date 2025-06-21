package com.study.demo.modules.project;

import com.study.demo.modules.file.model.FileEditionDto;
import com.study.demo.modules.project.mapper.ProjectResponseMapper;
import com.study.demo.modules.project.model.ProjectCreationDto;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
            //if(e.getClass().isInstance(ExceptionHandler.class)) {
            throw e;
            //}
            //return ResponseEntity.status(400).body(Map.of("fail", e.getMessage()));

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        try {
            ProjectResponseMapper project = ProjectResponseMapper.fromEntity(projectService.findById(UUID.fromString(id)));
            return ResponseEntity.ok(project);
        } catch (Throwable e) {
            return ResponseEntity.status(400).body(e.getMessage());

        }
    }

//    @GetMapping("/by-owner/{owner}")
//    public ResponseEntity<?> getByOwner(@PathVariable String owner) {
//        return projectService.getByOwner(owner);
//    }

//    @PostMapping
//    public ResponseEntity<?> postProperty(@RequestBody ProjectModel property) {
//        URI location = projectService.create(property);
//        return ResponseEntity.created(location).build();
//    }

//    @PostMapping("/code-demo")
//    public ResponseEntity<?> codeDemo(@RequestBody DemoSession scriptDemo) {
//        return sessionService.codeDemo(scriptDemo);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteProperty(@PathVariable String id) {
//        return projectService.delete(id);
//    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<?> patchProperty(@PathVariable String id, @RequestBody ProjectModel property) {
//        return ResponseEntity.ok("not_ok");//sessionService.modify(id, property);
//
//    }

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
