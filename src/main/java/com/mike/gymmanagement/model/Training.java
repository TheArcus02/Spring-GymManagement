package com.mike.gymmanagement.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trainings")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Training extends DbObject {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "training_exercises",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private Set<Exercise> exercises = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "trainings", fetch = FetchType.LAZY)
    private Set<WorkoutPlan> workoutPlans = new HashSet<>();

    @Size(min = 2, max = 100, message = "Description must be between 2 and 100 characters")
    private String description;

    public Training(long date, String name, Set<Exercise> exercises, Set<WorkoutPlan> workoutPlans, String description) {
        super(date, name);
        this.exercises = exercises;
        this.workoutPlans = workoutPlans;
        this.description = description;
    }

    public Training() {

    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public void removeExercise(Exercise exercise) {
        this.exercises.remove(exercise);
    }

    public void addWorkoutPlan(WorkoutPlan workoutPlan) {
        this.workoutPlans.add(workoutPlan);
    }

    public void removeWorkoutPlan(WorkoutPlan workoutPlan) {
        this.workoutPlans.remove(workoutPlan);
    }

    // Getters and setters
    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Set<WorkoutPlan> getWorkoutPlans() {
        return workoutPlans;
    }

    public void setWorkoutPlans(Set<WorkoutPlan> workoutPlans) {
        this.workoutPlans = workoutPlans;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
