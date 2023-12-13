package com.mike.gymmanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Trainer extends DbObject {

    private String surname;
    private double salary;
    private List<Client> clients;

    public Trainer(int id, long date, String name, String surname, double salary) {
        super(id, date, name);
        this.surname = surname;
        this.salary = salary;
        this.clients = new ArrayList<>();
    }

    public void assignWorkoutPlan(Client client, WorkoutPlan workoutPlan) {
        client.setWorkoutPlan(workoutPlan);
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
