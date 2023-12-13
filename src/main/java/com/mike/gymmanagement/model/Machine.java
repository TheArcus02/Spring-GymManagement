package com.mike.gymmanagement.model;

import com.mike.gymmanagement.enums.ExerciseCategoryEnum;

public class Machine extends Equipment {
    private ExerciseCategoryEnum type;

    public Machine(int id, long date, String name, boolean occupied, ExerciseCategoryEnum type) {
        super(id, date, name, occupied);
        this.type = type;
    }

    public ExerciseCategoryEnum getType() {
        return type;
    }

    public void setType(ExerciseCategoryEnum type) {
        this.type = type;
    }
}
