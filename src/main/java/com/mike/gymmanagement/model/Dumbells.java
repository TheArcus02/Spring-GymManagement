package com.mike.gymmanagement.model;

import jakarta.persistence.Entity;

@Entity
public class Dumbells extends Equipment {
    private int pairWeight;

    public Dumbells(int id, long date, String name, boolean occupied, int pairWeight) {
        super(id, date, name, occupied);
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