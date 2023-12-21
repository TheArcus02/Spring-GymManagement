package com.mike.gymmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.jdbc.Work;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trainings")
public class Training extends DbObject {

    @ManyToMany
    @JoinTable(
            name = "training_exercises",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private Set<Exercise> exercises = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "trainings")
    private Set<WorkoutPlan> workoutPlans = new HashSet<>();

    public Training(long date, String name, Set<Exercise> exercises, Set<WorkoutPlan> workoutPlans) {
        super(date, name);
        this.exercises = exercises;
        this.workoutPlans = workoutPlans;
    }

    public Training() {

    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }
}
