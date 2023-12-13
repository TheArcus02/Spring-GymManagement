package com.mike.gymmanagement.model;

import com.mike.gymmanagement.enums.DifficultyEnum;

import java.util.List;

public class WorkoutPlan extends DbObject {
    private String description;
    private List<Training> trainings;

    private DifficultyEnum difficulty;


    public WorkoutPlan(int id, long date, String name, String description, List<Training> trainings, DifficultyEnum difficulty) {
        super(id, date, name);
        this.description = description;
        this.trainings = trainings;
        this.difficulty = difficulty;
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
