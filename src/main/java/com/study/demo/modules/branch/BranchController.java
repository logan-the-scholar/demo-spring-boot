package com.study.demo.modules.branch;

import com.study.demo.modules.branch.model.BranchCreationDto;
import com.study.demo.modules.branch.service.BranchService;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("branch")
public class BranchController {

    @Autowired
    private final BranchService branchService;

    @Qualifier("localValidatorFactoryBean")
    private final Validator validator;

    public BranchController(BranchService branchService, Validator validator) {
        this.branchService = branchService;
        this.validator = validator;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> create(@PathVariable String id, @RequestBody @Valid BranchCreationDto body) {
        try {
            branchService.create(UUID.fromString(id), body.getName());
            return ResponseEntity.status(200).body(Map.of("message", body.getName() + " successfully created"));
        } catch (Throwable e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/default/{id}")
    public ResponseEntity<?> createDefault(@PathVariable String id) {
        try {
            branchService.createDefault(UUID.fromString(id));
            return ResponseEntity.status(200).body(Map.of("message", "main successfully created"));
        } catch (Throwable e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
