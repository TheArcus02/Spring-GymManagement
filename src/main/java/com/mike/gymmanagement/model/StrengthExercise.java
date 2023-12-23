package com.mike.gymmanagement.model;

import com.mike.gymmanagement.enums.DifficultyEnum;
import com.mike.gymmanagement.enums.ExerciseCategoryEnum;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;

@Entity
public class StrengthExercise extends Exercise {

    @Min(value = 1, message = "Invalid number of reps")
    private int reps;

    @Min(value = 1, message = "Invalid number of sets")
    private int sets;

    @Min(value = 0, message = "Invalid weight")
    private int weight;

    public StrengthExercise(long date, String name, DifficultyEnum difficulty, ExerciseCategoryEnum category, Equipment equipment, int reps, int sets, int weight) {
        super(date, name, difficulty, category, equipment);
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
    }

    public StrengthExercise() {

    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
