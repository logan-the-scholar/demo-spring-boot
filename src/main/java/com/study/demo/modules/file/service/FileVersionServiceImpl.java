package com.study.demo.modules.file.service;

import com.study.demo.modules.file.repository.FileVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class FileVersionServiceImpl implements FileVersionService{

    @Autowired
    private final FileVersionRepository repository;

    public FileVersionServiceImpl(FileVersionRepository repository) {
        this.repository = repository;
    }

    public void create() {

    }
}
