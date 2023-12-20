package com.mike.gymmanagement.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class DbObject {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dbobject_id_seq")
    @SequenceGenerator(name = "dbobject_id_seq",
            sequenceName = "dbobject_id_seq",
            allocationSize = 1)
    private long id;
    private long date;
    private String name;

    public DbObject(int id, long date, String name) {
        this.id = id;
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
