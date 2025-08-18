package com.study.demo.modules.file.model;

import com.study.demo.modules.project.model.ProjectModel;
import com.study.demo.modules.user.model.UserModel;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity(name = "files")
public class FileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private byte[] content;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectModel project;

    private String extension;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private UserModel author;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", orphanRemoval = true)
    private List<FileModel> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private FileModel parent;

    @Column(name = "full_path")
    private List<UUID> fullPath = new ArrayList<>();

    public FileModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public List<FileModel> getChildren() {
        return children;
    }

    public void setChildren(List<FileModel> children) {
        this.children = children;
    }

    public Optional<FileModel> getParent() {
        return Optional.ofNullable(this.parent);
    }

    public void setParent(FileModel parent) {
        this.parent = parent;
    }

    public List<UUID> getFullPath() {
        return fullPath;
    }

    public void setFullPath(List<UUID> fullPath) {
        this.fullPath = fullPath;
    }
}
