package com.study.demo.modules.file.model;

import com.study.demo.modules.project.model.ProjectModel;
import com.study.demo.modules.user.model.UserModel;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//    @Column(nullable = false)
//    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectModel project;

//    private String extension;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private UserModel author;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", orphanRemoval = true)
//    private List<File> children = new ArrayList<>();
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
//    private File parent;
//
//    @Column(name = "full_path")
//    private List<UUID> fullPath = new ArrayList<>();

    public File() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }

//    public String getExtension() {
//        return extension;
//    }
//
//    public void setExtension(String extension) {
//        this.extension = extension;
//    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

//    public List<File> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<File> children) {
//        this.children = children;
//    }
//
//    public Optional<File> getParent() {
//        return Optional.ofNullable(this.parent);
//    }
//
//    public void setParent(File parent) {
//        this.parent = parent;
//    }
//
//    public List<UUID> getFullPath() {
//        return fullPath;
//    }
//
//    public void setFullPath(List<UUID> fullPath) {
//        this.fullPath = fullPath;
//    }
}
