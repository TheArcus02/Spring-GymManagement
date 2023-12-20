package com.mike.gymmanagement.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "trainings")
public class Training extends DbObject {

    @ManyToMany
    @JoinTable(
            name = "training_exercise",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<Exercise> exercises;

    public Training(int id, long date, String name, List<Exercise> exercises) {
        super(id, date, name);
        this.exercises = exercises;
    }

    public Training() {

    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
