package com.mike.gymmanagement.model;

import jakarta.persistence.Entity;

@Entity
public class Barbell extends Equipment {
    private int weight;

    public Barbell(long date, String name, boolean occupied, int weight) {
        super(date, name, occupied);
        this.weight = weight;
    }

    public Barbell() {

    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if (weight > 0) {
            this.weight = weight;
        }
    }
}
