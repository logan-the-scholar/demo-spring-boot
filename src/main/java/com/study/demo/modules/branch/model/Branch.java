package com.study.demo.modules.branch.model;

import com.study.demo.modules.commit.model.Commit;
import com.study.demo.modules.project.model.Project;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Project project;

    @OneToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "head_commit")
    private Commit headCommit;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "draft_commit")
    private Commit draftCommit;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Commit> commits;

    public Branch() {
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Commit getHeadCommit() {
        return headCommit;
    }

    public void setHeadCommit(Commit headCommit) {
        this.headCommit = headCommit;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Commit getDraftCommit() {
        return draftCommit;
    }

    public void setDraftCommit(Commit draftCommit) {
        this.draftCommit = draftCommit;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }
}
