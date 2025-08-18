package com.study.demo.modules.commit.model;

import com.study.demo.modules.project.model.ProjectModel;
import com.study.demo.modules.user.model.UserModel;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "commit")
public class CommitModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "project")
    private ProjectModel project;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel author;

    @Column(nullable = false)
    private String status;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    private CommitModel parent;

    public CommitModel() {
    }

    public UUID getId() {
        return id;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }

    public void setId(UUID id) {
        this.id = id;
    }



    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CommitModel getParent() {
        return parent;
    }

    public void setParent(CommitModel parent) {
        this.parent = parent;
    }
}
