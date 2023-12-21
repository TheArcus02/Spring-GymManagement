package com.mike.gymmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Equipment extends DbObject {
    private boolean occupied;

    @JsonIgnore
    @OneToMany(mappedBy = "equipment")
    private List<Exercise> exercises;

    public Equipment(long date, String name, boolean occupied) {
        super(date, name);
        this.occupied = occupied;
    }

    public Equipment() {

    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
