package com.mike.gymmanagement.model;

import com.mike.gymmanagement.enums.ExerciseCategoryEnum;
import jakarta.persistence.Entity;

@Entity
public class Machine extends Equipment {

    // No validation
    private ExerciseCategoryEnum category;

    public Machine(long date, String name, boolean occupied, ExerciseCategoryEnum category) {
        super(date, name, occupied);
        this.category = category;
    }

    public Machine() {

    }

    public ExerciseCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(ExerciseCategoryEnum type) {
        this.category = type;
    }
}
