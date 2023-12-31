package com.mike.gymmanagement.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trainers")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Trainer extends DbObject {

    @NotBlank(message = "Surname cannot be empty")
    @Size(max = 30, message = "Surname cannot be longer than 30 characters")
    private String surname;

    @Min(value = 0, message = "Salary cannot be negative")
    private double salary;

    @JsonManagedReference
    @OneToMany(mappedBy = "trainer", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
