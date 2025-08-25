package com.study.demo.modules.user.model;

import com.study.demo.modules.file.model.File;
import com.study.demo.modules.workspace.model.WorkspaceModel;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @Column(unique = true)
    private String sub;

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<WorkspaceModel> workspaces = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST}, orphanRemoval = true)
    private List<File> authoredFiles = new ArrayList<>();

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profile_image) {
        this.profileImage = profile_image;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<WorkspaceModel> getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(List<WorkspaceModel> workspaces) {
        this.workspaces = workspaces;
    }

    public List<File> getAuthoredFiles() {
        return authoredFiles;
    }

    public void setAuthoredFiles(List<File> authoredFiles) {
        this.authoredFiles = authoredFiles;
    }
}
