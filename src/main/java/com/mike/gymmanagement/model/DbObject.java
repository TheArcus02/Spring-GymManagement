package com.mike.gymmanagement.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class DbObject {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long date;
    private String name;

    public DbObject(long date, String name) {
        this.date = date;
        this.name = name;
    }

    public DbObject() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
