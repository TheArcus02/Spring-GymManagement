package com.mike.gymmanagement.model;

import java.util.List;

public class Training extends DbObject {
    private List<Exercise> exercises;

    public Training(int id, long date, String name, List<Exercise> exercises) {
        super(id, date, name);
        this.exercises = exercises;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
