package com.study.demo.modules.branch;

import com.study.demo.modules.branch.model.BranchCreationDto;
import com.study.demo.modules.branch.model.BranchResponseMapper;
import com.study.demo.modules.branch.service.BranchService;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@Deprecated
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

//    @PostMapping("/{repo}")
//    public ResponseEntity<?> create(@PathVariable String repo, @RequestBody @Valid BranchCreationDto body) {
//        try {
//            branchService.create(UUID.fromString(repo), body);
//            return ResponseEntity.status(200).body(Map.of("message", body.getName() + " successfully created"));
//        } catch (Throwable e) {
//            return ResponseEntity.status(400).body(e.getMessage());
//        }
//    }

//    @PostMapping("/default/{id}")
//    public ResponseEntity<?> createDefault(@PathVariable String id) {
//        try {
//            branchService.createDefault(UUID.fromString(id));
//            return ResponseEntity.status(200).body(Map.of("message", "main successfully created"));
//        } catch (Throwable e) {
//            return ResponseEntity.status(400).body(e.getMessage());
//        }
//    }

//    @GetMapping("/{repo}/branch/{branch}")
//    public ResponseEntity<?> getAndFiles(@PathVariable String repo, @PathVariable String branch) {
//        try {
//            BranchResponseMapper branchResponse = branchService.getFromHead(UUID.fromString(repo), branch);
//            return ResponseEntity.ok(branchResponse);
//        } catch (Throwable e) {
//            return ResponseEntity.status(400).body(e.getMessage());
//        }
//    }

}
