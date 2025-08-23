package com.study.demo.modules.branch.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BranchCreationDto {
    @NotBlank
    @Size(min = 4, max = 20, message = "Branch name must be between 4 - 20 characters")
    private String name;

    @NotBlank
    @Size(min = 4, max = 20, message = "Branch name must be between 4 - 20 characters")
    private String fromBranch;

    public @NotBlank @Size(min = 4, max = 20, message = "Branch name must be between 4 - 20 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(min = 4, max = 20, message = "Branch name must be between 4 - 20 characters") String name) {
        this.name = name;
    }

    public @NotBlank @Size(min = 4, max = 20, message = "Branch name must be between 4 - 20 characters") String getFromBranch() {
        return fromBranch;
    }

    public void setFromBranch(@NotBlank @Size(min = 4, max = 20, message = "Branch name must be between 4 - 20 characters") String fromBranch) {
        this.fromBranch = fromBranch;
    }
}
