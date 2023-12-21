package com.mike.gymmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trainers")
public class Trainer extends DbObject {

    private String surname;
    private double salary;

    @JsonIgnore
    @OneToMany(mappedBy = "trainer", fetch = FetchType.EAGER)
    private Set<Client> clients = new HashSet<>();

    public Trainer(long date, String name, String surname, double salary) {
        super(date, name);
        this.surname = surname;
        this.salary = salary;
    }

    public Trainer() {
        super();
    }

    public void assignWorkoutPlan(Client client, WorkoutPlan workoutPlan) {
        client.setWorkoutPlan(workoutPlan);
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
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
