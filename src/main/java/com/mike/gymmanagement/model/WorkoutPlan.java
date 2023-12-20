package com.mike.gymmanagement.model;

import com.mike.gymmanagement.enums.DifficultyEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "workout_plans")
public class WorkoutPlan extends DbObject {
    private String description;

    @ManyToMany
    @JoinTable(
            name = "workout_plan_training",
            joinColumns = @JoinColumn(name = "workout_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "training_id")
    )
    private List<Training> trainings;

    private DifficultyEnum difficulty;


    public WorkoutPlan(int id, long date, String name, String description, List<Training> trainings, DifficultyEnum difficulty) {
        super(id, date, name);
        this.description = description;
        this.trainings = trainings;
        this.difficulty = difficulty;
    }

    public WorkoutPlan() {

    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
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
