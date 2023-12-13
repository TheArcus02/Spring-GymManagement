package com.mike.gymmanagement.model;

public class Dumbells extends Equipment {
    private int pairWeight;

    public Dumbells(int id, long date, String name, boolean occupied, int pairWeight) {
        super(id, date, name, occupied);
        this.pairWeight = pairWeight;
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
