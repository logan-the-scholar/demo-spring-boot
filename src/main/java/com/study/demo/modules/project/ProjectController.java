package com.study.demo.modules.project;

import com.study.demo.modules.file.model.EditedFileDto;
import com.study.demo.modules.project.model.ProjectCreationDto;
import com.study.demo.modules.project.model.ProjectModel;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ProjectModel>> getAll() {
        return ResponseEntity.ok(projectService.getAll());
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
    public EditedFileDto broadcastEditCode(@DestinationVariable String id, @RequestBody @Valid EditedFileDto editedFile) {
        return editedFile;

    }

    @PostMapping()
    public ResponseEntity<?> createProject(@RequestBody @Valid ProjectCreationDto newProject) {
        try {
            projectService.createProject(newProject);
            return ResponseEntity.ok("created nicely");

        } catch (Throwable error) {
            System.err.println(error);
            return ResponseEntity.status(400).body(error.getMessage());

        }
    }
}
