package com.mike.gymmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Dumbells.class, name = "Dumbells"),
        @JsonSubTypes.Type(value = Barbell.class, name = "Barbell"),
        @JsonSubTypes.Type(value = Machine.class, name = "Machine"),
})
public abstract class Equipment extends DbObject {
    private boolean occupied;

    @JsonIgnore
    @OneToMany(mappedBy = "equipment")
    private List<Exercise> exercises;

    @NotBlank
    @JsonProperty
    private String type;

    public Equipment(String name, boolean occupied) {
        super(name);
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

    public void removeExercise(Exercise exercise) {
        exercises.remove(exercise);
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
