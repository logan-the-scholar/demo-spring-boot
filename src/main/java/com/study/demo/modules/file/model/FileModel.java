package com.study.demo.modules.file.model;

import com.study.demo.modules.project.model.ProjectModel;
import com.study.demo.modules.user.model.UserModel;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "files")
public class FileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    private String content;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectModel project;
    private String extension;
//    private List<String> path;
//    private List<String> pathNames;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private UserModel author;

    @ManyToMany
    @JoinTable(
            name = "child_files",
            joinColumns = @JoinColumn(name = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "child_file_id")
    )
    private List<FileModel> childFiles = new ArrayList<>();

    @ManyToMany(mappedBy = "childFiles", cascade = CascadeType.PERSIST)
    private List<FileModel> path = new ArrayList<>();

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
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

    public List<FileModel> getChildFiles() {
        return childFiles;
    }

    public void setChildFiles(List<FileModel> childFiles) {
        this.childFiles = childFiles;
    }

    public List<FileModel> getPath() {
        return path;
    }

    public void setPath(List<FileModel> path) {
        this.path = path;
    }
}
