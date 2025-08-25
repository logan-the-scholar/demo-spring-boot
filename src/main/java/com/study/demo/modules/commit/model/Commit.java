package com.study.demo.modules.commit.model;

import com.study.demo.modules.branch.model.Branch;
import com.study.demo.modules.file.model.FileVersion;
import com.study.demo.modules.project.model.Project;
import com.study.demo.modules.user.model.User;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "commit")
public class Commit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Project project;

    @Column(nullable = true)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @Column(nullable = false)
    private String status;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    private Commit parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commit", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<FileVersion> files;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "branch", nullable = true)
    private Branch branch;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    public Commit() {
    }

    public UUID getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Commit getParent() {
        return parent;
    }

    public void setParent(Commit parent) {
        this.parent = parent;
    }

    public List<FileVersion> getFiles() {
        return files;
    }

    public void setFiles(List<FileVersion> files) {
        this.files = files;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
