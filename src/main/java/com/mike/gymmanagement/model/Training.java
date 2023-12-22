package com.mike.gymmanagement.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.jdbc.Work;

import java.util.HashSet;
import java.util.List;
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

    public Set<WorkoutPlan> getWorkoutPlans() {
        return workoutPlans;
    }

    public void setWorkoutPlans(Set<WorkoutPlan> workoutPlans) {
        this.workoutPlans = workoutPlans;
    }
}
