package com.mike.gymmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public abstract class DbObject {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long date;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 30, message = "Name cannot be longer than 30 characters")
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
