package com.mike.gymmanagement.model;

import com.mike.gymmanagement.enums.ExerciseCategoryEnum;
import jakarta.persistence.Entity;

@Entity
public class Machine extends Equipment {
    private ExerciseCategoryEnum type;

    public Machine(long date, String name, boolean occupied, ExerciseCategoryEnum type) {
        super(date, name, occupied);
        this.type = type;
    }

    public Machine() {

    }

    public ExerciseCategoryEnum getType() {
        return type;
    }

    public void setType(ExerciseCategoryEnum type) {
        this.type = type;
    }
}
