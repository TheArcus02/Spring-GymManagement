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
    private boolean isOccupied;

    private Long occupiedBy;

    @JsonIgnore
    @OneToMany(mappedBy = "equipment")
    private List<Exercise> exercises;

    @NotBlank
    @JsonProperty
    private String type;

    public Equipment(String name, boolean isOccupied, Long occupiedBy) {
        super(name);
        this.isOccupied = isOccupied;
        this.occupiedBy = occupiedBy;
    }

    public Equipment() {

    }

    public void removeExercise(Exercise exercise) {
        exercises.remove(exercise);
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + getId() +
                ", name='" + getName() +
                ", occupied=" + isOccupied +
                ", type='" + type +
                '}';
    }

    //    Getters and setters

    public boolean isIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean occupied) {
        this.isOccupied = occupied;
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

    public Long getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(Long occupiedBy) {
        this.occupiedBy = occupiedBy;
    }
}
