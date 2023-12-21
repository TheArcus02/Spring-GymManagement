package com.mike.gymmanagement.model;

import com.mike.gymmanagement.enums.DifficultyEnum;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "workout_plans")
public class WorkoutPlan extends DbObject {
    private String description;

    @ManyToMany
    @JoinTable(
            name = "workout_plan_trainings",
            joinColumns = @JoinColumn(name = "workout_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "training_id")
    )
    private Set<Training> trainings = new HashSet<>();

    private DifficultyEnum difficulty;


    public WorkoutPlan(long date, String name, String description, Set<Training> trainings, DifficultyEnum difficulty) {
        super(date, name);
        this.description = description;
        this.trainings = trainings;
        this.difficulty = difficulty;
    }

    public WorkoutPlan() {

    }

    public Set<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public DifficultyEnum getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
    }
}
