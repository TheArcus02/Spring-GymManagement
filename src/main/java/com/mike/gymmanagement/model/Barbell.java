package com.mike.gymmanagement.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;

@Entity
public class Barbell extends Equipment {

    @Min(value = 0, message = "Invalid weight")
    private int weight;

    public Barbell(String name, boolean occupied, int weight, Long occupiedBy) {
        super(name, occupied, occupiedBy);
        this.weight = weight;
    }

    public Barbell() {

    }

    @Override
    public String toString() {
        return "Barbell{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", occupied=" + isIsOccupied() +
                ", type='" + getType() +
                ", weight=" + weight +
                '}';
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
