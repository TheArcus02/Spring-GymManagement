package com.mike.gymmanagement.model;

public class Client extends DbObject {
    private String surname;
    private double weight;
    private String email;


    public Client(int id, long date, String name, String surname, double weight, String email) {
        super(id, date, name);
        this.surname = surname;
        this.weight = weight;
        this.email = email;
    }

//    Start trainng and stop training

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
