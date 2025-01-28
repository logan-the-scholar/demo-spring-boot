package com.study.demo.model;

public class PropertyModel {
    private int ID;
    private String owner;
    private String address;
    private int rooms;

    public PropertyModel(int ID, String owner, String address, int rooms) {
        this.ID = ID;
        this.owner = owner;
        this.address = address;
        this.rooms = rooms;
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

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getID() {
        return ID;
    }
}
