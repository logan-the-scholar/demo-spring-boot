package com.study.demo.modules.token.model;

import com.study.demo.modules.user.model.User;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "user_token")
public class UserTokenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private String access;

    @Column(name = "expires_in", nullable = false)
    private Long expiresIn;

    @Column(nullable = false)
    private String refresh;

    @Column(name = "refresh_expires_in", nullable = false)
    private Long refreshExpiresIn;

    public UserTokenModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public Long getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public void setRefreshExpiresIn(long refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }
}
