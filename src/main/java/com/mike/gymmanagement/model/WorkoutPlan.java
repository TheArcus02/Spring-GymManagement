package com.mike.gymmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mike.gymmanagement.enums.DifficultyEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workout_plans")
public class WorkoutPlan extends DbObject {

    @Size(min = 2, max = 100, message = "Description must be between 2 and 100 characters")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "workout_plan_trainings",
            joinColumns = @JoinColumn(name = "workout_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "training_id")
    )
    private Set<Training> trainings = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "workoutPlan", fetch = FetchType.EAGER)
    private Set<Client> clients = new HashSet<>();

    // not validated
    private DifficultyEnum difficulty;


    public WorkoutPlan(String name, String description, Set<Training> trainings, DifficultyEnum difficulty) {
        super(name);
        this.description = description;
        this.trainings = trainings;
        this.difficulty = difficulty;
    }

    public WorkoutPlan() {

    }


    public void removeClient(Client client) {
        this.clients.remove(client);
    }

    @Override
    public String toString() {
        return "WorkoutPlan{" +
                "id=" + this.getId() +
                ", name='" + this.getName() +
                ", description='" + description +
                ", trainings=" + trainings +
                ", difficulty=" + difficulty +
                '}';
    }

    //    Getters and setters
    public Set<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }

    public void addTraining(Training training) {
        this.trainings.add(training);
    }

    public void removeTraining(Training training) {
        this.trainings.remove(training);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client client) {
        this.clients.add(client);
    }

    public DifficultyEnum getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
    }
}
