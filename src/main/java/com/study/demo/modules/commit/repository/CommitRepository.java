package com.study.demo.modules.commit.repository;

import com.study.demo.modules.commit.model.CommitModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommitRepository extends JpaRepository<CommitModel, UUID> {
}
