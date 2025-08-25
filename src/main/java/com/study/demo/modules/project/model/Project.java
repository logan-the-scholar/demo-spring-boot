package com.study.demo.modules.project.model;

import com.study.demo.modules.branch.model.Branch;
import com.study.demo.modules.workspace.model.WorkspaceModel;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectVisibility visibility;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", nullable = false)
    private WorkspaceModel workspace;

//    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
//    private List<File> files = new ArrayList<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Column(name = "branches_ids")
    private List<Branch> branches;

    public Project() {
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

    public WorkspaceModel getWorkspace() {
        return workspace;
    }

    public void setWorkspace(WorkspaceModel workspace) {
        this.workspace = workspace;
    }

//    public List<File> getFiles() {
//        return files;
//    }
//
//    public void setFiles(List<File> files) {
//        this.files = files;
//    }

    public ProjectVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(ProjectVisibility visibility) {
        this.visibility = visibility;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
