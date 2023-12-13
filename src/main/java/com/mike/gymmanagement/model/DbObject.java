package com.mike.gymmanagement.model;

public abstract class DbObject {
    private int id;
    private long date;
    private String name;

    public DbObject(int id, long date, String name) {
        this.id = id;
        this.date = date;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
