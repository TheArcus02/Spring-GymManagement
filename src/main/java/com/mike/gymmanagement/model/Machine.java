package com.mike.gymmanagement.model;

import com.mike.gymmanagement.enums.ExerciseCategoryEnum;
import jakarta.persistence.Entity;

@Entity
public class Machine extends Equipment {

    // No validation
    private ExerciseCategoryEnum category;

    public Machine(String name, boolean occupied, ExerciseCategoryEnum category, Long occupiedBy) {
        super(name, occupied, occupiedBy);
        this.category = category;
    }

    public Machine() {

    }

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", occupied=" + isIsOccupied() +
                ", type='" + getType() +
                ", category=" + category +
                '}';
    }

    public ExerciseCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(ExerciseCategoryEnum type) {
        this.category = type;
    }
}
