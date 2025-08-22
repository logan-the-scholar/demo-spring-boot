package com.study.demo.modules.branch.model;

import com.study.demo.modules.file.model.FileResponseMapper;
import com.study.demo.modules.file.model.FileVersion;

import java.util.List;
import java.util.UUID;

public record BranchResponseMapper(String name, UUID id, UUID uuid, List<FileResponseMapper> files) {
    public static BranchResponseMapper fromEntity(Branch branch, List<FileResponseMapper> files) {
        return new BranchResponseMapper(
                branch.getName(),
                branch.getHeadCommit().getId(),
                branch.getDraftCommit().getId(),
                files
        );
    }
}
