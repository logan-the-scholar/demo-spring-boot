package com.study.demo.modules.file;

import com.study.demo.modules.file.mapper.FileResponseMapper;
import com.study.demo.modules.file.model.FileCreationDto;
import com.study.demo.modules.file.model.FileEditionDto;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("file")
public class FileController {

    @Autowired
    private final FileService fileService;

    @Qualifier("localValidatorFactoryBean")
    private final Validator validator;

    public FileController(FileService fileService, Validator validator) {
        this.fileService = fileService;
        this.validator = validator;
    }

    @CrossOrigin(exposedHeaders = "Location")
    @PostMapping("/{id}")
    public ResponseEntity<?> create(@PathVariable String id, @RequestBody @Valid FileCreationDto bodyFile) {
        try {
            FileResponseMapper createdFile = fileService.create(UUID.fromString(id), bodyFile);
            return ResponseEntity.status(401).body(createdFile);
        } catch (Throwable e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable String id, @RequestBody @Valid FileEditionDto bodyFile) {
        try {
            FileResponseMapper updatedFile = fileService.update(UUID.fromString(id), bodyFile);
            return ResponseEntity.status(401).body(updatedFile);
        } catch (Throwable e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
