package com.mike.gymmanagement.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;

@Entity
public class Dumbells extends Equipment {

    @Min(value = 0, message = "Invalid weight")
    private int weight;

    public Dumbells(String name, boolean occupied, int weight) {
        super(name, occupied);
        this.weight = weight;
    }

    public Dumbells() {

    }

    @Override
    public String toString() {
        return "Dumbells{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", occupied=" + isOccupied() +
                ", type='" + getType() +
                ", weight=" + weight +
                '}';
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
