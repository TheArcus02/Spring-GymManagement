package com.mike.gymmanagement.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;

@Entity
public class Dumbells extends Equipment {

    @Min(value = 0, message = "Invalid weight")
    private int pairWeight;

    public Dumbells(long date, String name, boolean occupied, int pairWeight) {
        super(date, name, occupied);
        this.pairWeight = pairWeight;
    }

    public Dumbells() {

    }

    public int getPairWeight() {
        return pairWeight;
    }

    public void setPairWeight(int pairWeight) {
        if (pairWeight > 0) {
            this.pairWeight = pairWeight;
        }
    }
}
