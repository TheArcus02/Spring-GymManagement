package com.mike.gymmanagement.model;

import com.mike.gymmanagement.enums.ExerciseCategoryEnum;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Machine extends Equipment {
    
    @Min(value = 0, message = "Invalid exercise category")
    @Max(value = 3, message = "Invalid exercise category")
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
