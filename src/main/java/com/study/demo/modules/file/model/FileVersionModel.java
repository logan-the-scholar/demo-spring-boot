package com.study.demo.modules.file.model;

import com.study.demo.modules.commit.model.CommitModel;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "file_version")
public class FileVersionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private FileModel file;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CommitModel commit;

    private byte[] content;

    public FileVersionModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public FileModel getFile() {
        return file;
    }

    public void setFile(FileModel file) {
        this.file = file;
    }

    public CommitModel getCommit() {
        return commit;
    }

    public void setCommit(CommitModel commit) {
        this.commit = commit;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
