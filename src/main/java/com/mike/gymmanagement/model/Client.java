package com.mike.gymmanagement.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client extends DbObject {
    private String surname;
    private double weight;
    private String email;

    @ManyToOne
    @JoinColumn(name = "workout_plan_id")
    private WorkoutPlan workoutPlan;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    public Client(int id, long date, String name, String surname, double weight, String email, WorkoutPlan workoutPlan) {
        super(id, date, name);
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
}
