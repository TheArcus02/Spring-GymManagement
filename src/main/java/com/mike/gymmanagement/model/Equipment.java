package com.mike.gymmanagement.model;

public abstract class Equipment extends DbObject {
    private boolean occupied;

    public Equipment(int id, long date, String name, boolean occupied) {
        super(id, date, name);
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
