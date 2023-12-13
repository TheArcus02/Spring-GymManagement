package com.mike.gymmanagement.model;

public class Training extends DbObject {
    private Exercise[] exercises;

    public Training(int id, long date, String name, Exercise[] exercises) {
        super(id, date, name);
        this.exercises = exercises;
    }

    public Exercise[] getExercises() {
        return exercises;
    }

    public void setExercises(Exercise[] exercises) {
        this.exercises = exercises;
    }
}
