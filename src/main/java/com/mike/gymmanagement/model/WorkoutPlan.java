package com.mike.gymmanagement.model;

import com.mike.gymmanagement.enums.DifficultyEnum;

public class WorkoutPlan extends DbObject {
    private String description;
    private Training[] trainings;

    private DifficultyEnum difficulty;


    public WorkoutPlan(int id, long date, String name, String description, Training[] trainings, DifficultyEnum difficulty) {
        super(id, date, name);
        this.description = description;
        this.trainings = trainings;
        this.difficulty = difficulty;
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
