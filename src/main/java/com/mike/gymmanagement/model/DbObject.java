package com.mike.gymmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class DbObject {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime createdAt;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 30, message = "Name cannot be longer than 30 characters")
    private String name;

    public DbObject(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "DbObject{" +
                "id='" + this.id +
                ", name=" + this.name +
                '}';
    }

    public DbObject() {
        this.createdAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
