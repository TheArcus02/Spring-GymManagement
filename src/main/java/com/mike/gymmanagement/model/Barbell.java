package com.mike.gymmanagement.model;

public class Barbell extends Equipment {
    private int weight;

    public Barbell(int id, long date, String name, boolean occupied, int weight) {
        super(id, date, name, occupied);
        this.weight = weight;
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
