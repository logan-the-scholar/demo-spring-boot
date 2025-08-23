package com.study.demo.modules.branch.repository;

import com.study.demo.modules.branch.model.Branch;
import com.study.demo.modules.project.model.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BranchRepository extends JpaRepository<Branch, UUID> {
    Optional<Branch> findByProjectAndName(ProjectModel project, String name);
}
