package com.study.demo.modules.workspace.repository;

import com.study.demo.modules.user.model.User;
import com.study.demo.modules.workspace.model.WorkspaceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkspaceRepository extends JpaRepository<WorkspaceModel, UUID> {
    List<WorkspaceModel> findByOwner(User owner);
}
