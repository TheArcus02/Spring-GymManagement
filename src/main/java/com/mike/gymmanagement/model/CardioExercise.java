package com.mike.gymmanagement.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mike.gymmanagement.enums.DifficultyEnum;
import com.mike.gymmanagement.enums.ExerciseCategoryEnum;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;

@Entity
@JsonTypeName("CardioExercise")
public class CardioExercise extends Exercise {

    @Min(value = 1, message = "Invalid duration")
    private int duration;

    @Min(value = 1, message = "Invalid tempo")
    private int tempo;

    public CardioExercise(String name, DifficultyEnum difficulty, ExerciseCategoryEnum category, Equipment equipment, int duration, int tempo) {
        super(name, difficulty, category, equipment);
        this.duration = duration;
        this.tempo = tempo;
    }

    public CardioExercise() {
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
