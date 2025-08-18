package com.study.demo.modules.branch.repository;

import com.study.demo.modules.branch.model.BranchModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BranchRepository extends JpaRepository<BranchModel, UUID> {
}
