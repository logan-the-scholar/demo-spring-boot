package com.study.demo.modules.workspace;

import com.study.demo.modules.workspace.mapper.WorkspaceResponseMapper;
import com.study.demo.modules.workspace.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("workspace")
public class WorkspaceController {

    @Autowired
    private final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkspaces(@PathVariable String id) {
        try {
            List<WorkspaceResponseMapper> workspaces = workspaceService.findAllById(UUID.fromString(id));
            return ResponseEntity.status(200).body(workspaces);

        } catch (Throwable error) {
            System.err.println(error);
            return ResponseEntity.status(400).body(error.getMessage());

        }
    }

    @PostMapping()
    public ResponseEntity<?> createWorkspace(@PathVariable String id, @RequestBody String nose) {
        return ResponseEntity.status(200).body("not implemented yet");
    }

}
