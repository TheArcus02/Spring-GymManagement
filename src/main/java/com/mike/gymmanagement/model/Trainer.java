package com.mike.gymmanagement.model;

public class Trainer extends DbObject {

    private String surname;
    private double salary;
//    private clients[] clients;

    public Trainer(int id, long date, String name, String surname, double salary) {
        super(id, date, name);
        this.surname = surname;
        this.salary = salary;
    }

//    public void assignWorkoutPlan(client: Client, workoutPlan: WorkoutPlan){
//
//    }


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
