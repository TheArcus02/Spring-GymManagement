package com.mike.gymmanagement.model;

import com.mike.gymmanagement.enums.DifficultyEnum;
import com.mike.gymmanagement.enums.ExerciseCategoryEnum;

public class CardioExercise extends Exercise {
    private int duration;
    private int tempo;

    public CardioExercise(int id, long date, String name, DifficultyEnum difficulty, ExerciseCategoryEnum category, Equipment equipment, int duration, int tempo) {
        super(id, date, name, difficulty, category, equipment);
        this.duration = duration;
        this.tempo = tempo;
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
