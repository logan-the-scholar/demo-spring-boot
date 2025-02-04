package com.study.demo.model;

public class PropertyModel {
    private String ID;
    private String owner;
    private String address;
    private Integer rooms;
    private Integer bathrooms;
    private Boolean was_deleted;

    public PropertyModel() {

    }

    public PropertyModel(String ID, String owner, String address, int rooms, int bathrooms) {
        this.ID = ID;
        this.owner = owner;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
