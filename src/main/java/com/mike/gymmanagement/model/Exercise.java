package com.mike.gymmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.mike.gymmanagement.enums.DifficultyEnum;
import com.mike.gymmanagement.enums.ExerciseCategoryEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StrengthExercise.class, name = "StrengthExercise"),
        @JsonSubTypes.Type(value = CardioExercise.class, name = "CardioExercise")
})
public abstract class Exercise extends DbObject {

    // no validation
    private DifficultyEnum difficulty;

    // no validation
    private ExerciseCategoryEnum category;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "equipment_id", referencedColumnName = "id")
    private Equipment equipment;

    @JsonIgnore
    @ManyToMany(mappedBy = "exercises", fetch = FetchType.EAGER)
    private Set<Training> trainings = new HashSet<>();

    @NotBlank(message = "Type is mandatory")
    @JsonProperty
    private String type;


    public Exercise(String name, DifficultyEnum difficulty, ExerciseCategoryEnum category, Equipment equipment) {
        super(name);
        this.difficulty = difficulty;
        this.category = category;
        this.equipment = equipment;
    }

    public Exercise() {
    }

    public void occupyEquipment(Client client) {
        if (equipment.isIsOccupied()) {
            throw new IllegalStateException("Equipment is already occupied");
        }
        equipment.setIsOccupied(true);
        equipment.setOccupiedBy(client.getId());
    }

    public void freeEquipment() {
        equipment.setIsOccupied(false);
        equipment.setOccupiedBy(null);
    }

    public void removeTraining(Training training) {
        trainings.remove(training);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + this.getId() +
                ", name='" + this.getName() +
                ", difficulty=" + difficulty +
                ", category=" + category +
                ", equipment=" + equipment +
                '}';
    }

    //    Getters and setters

    public DifficultyEnum getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
    }

    public ExerciseCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(ExerciseCategoryEnum category) {
        this.category = category;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Set<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
