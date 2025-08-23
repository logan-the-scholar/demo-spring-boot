package com.study.demo.modules.commit.repository;

import com.study.demo.modules.commit.model.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommitRepository extends JpaRepository<Commit, UUID> {
}
