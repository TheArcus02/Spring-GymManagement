package com.mike.gymmanagement.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;

@Entity
public class Dumbells extends Equipment {

    @Min(value = 0, message = "Invalid weight")
    private int weight;

    public Dumbells(long date, String name, boolean occupied, int weight) {
        super(date, name, occupied);
        this.weight = weight;
    }

    public Dumbells() {

    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int pairWeight) {
        if (pairWeight > 0) {
            this.weight = pairWeight;
        }
    }
}
