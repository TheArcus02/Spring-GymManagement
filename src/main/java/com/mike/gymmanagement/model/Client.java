package com.mike.gymmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clients")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Client extends DbObject {

    @NotBlank(message = "Surname cannot be empty")
    @Size(max = 30, message = "Surname cannot be longer than 30 characters")
    private String surname;

    @Min(value = 0, message = "Weight cannot be negative")
    private double weight;

    @Email(message = "Invalid email address")
    private String email;

    @ManyToOne
    @JoinColumn(name = "workout_plan_id", referencedColumnName = "id")
    private WorkoutPlan workoutPlan;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Trainer trainer;

    @Column(name = "trainer_id", insertable = false, updatable = false)
    private Long trainerId;

    public Client(String name, String surname, double weight, String email, WorkoutPlan workoutPlan) {
        super(name);
        this.surname = surname;
        this.weight = weight;
        this.email = email;
        this.workoutPlan = workoutPlan;
    }

    public Client() {

    }

    //    Start trainng and stop training

    public void startTraining(Exercise exercise) {
        exercise.occupyEquipment();
    }

    public void stopTraining(Exercise exercise) {
        exercise.freeEquipment();
    }

    public void assignTrainer(Trainer trainer) {
        this.trainer = trainer;
        trainer.addClient(this);
    }

    public void removeWorkoutPlan() {
        this.workoutPlan = null;
    }

    //   Getters and setters
    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }

    public void setWorkoutPlan(WorkoutPlan workoutPlan) {
        this.workoutPlan = workoutPlan;
    }

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

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }
}
