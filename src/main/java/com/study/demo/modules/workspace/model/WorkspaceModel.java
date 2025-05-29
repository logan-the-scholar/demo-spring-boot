package com.study.demo.modules.workspace.model;

import com.study.demo.modules.user.model.UserModel;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "workspaces")
public class WorkspaceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private UserModel owner;

    //@OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    //private String projects;

    public WorkspaceModel() {
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

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }

//    public String getProjects() {
//        return projects;
//    }
//
//    public void setProjects(String projects) {
//        this.projects = projects;
//    }
}
