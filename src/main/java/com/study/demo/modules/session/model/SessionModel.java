package com.study.demo.modules.session.model;

import jakarta.persistence.*;

@Entity(name = "sessions")
public class SessionModel implements Session {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ID;
    private String owner;
    private String code;
    private String language;
    private Integer rooms;
    private Integer bathrooms;
    private Boolean was_deleted;

    public SessionModel() {

    }

    public SessionModel(String ID, String owner, String code, String language, int rooms, int bathrooms) {
        this.ID = ID;
        this.owner = owner;
        this.code = code;
        this.language = language;
        this.rooms = rooms;
        this.bathrooms = bathrooms;
        this.was_deleted = false;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Boolean getWas_deleted() {
        return was_deleted;
    }

    public void setWas_deleted(Boolean was_deleted) {
        this.was_deleted = was_deleted;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String address) {
        this.code = address;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getID() {
        return ID;
    }
}
