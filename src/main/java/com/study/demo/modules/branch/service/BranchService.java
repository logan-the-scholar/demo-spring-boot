package com.study.demo.modules.branch.service;

import java.util.UUID;

public interface BranchService {
    void create(UUID project, String name);
    void createDefault(UUID projectId);
}
