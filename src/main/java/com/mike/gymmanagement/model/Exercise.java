package com.mike.gymmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mike.gymmanagement.enums.DifficultyEnum;
import com.mike.gymmanagement.enums.ExerciseCategoryEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Exercise extends DbObject {

    @Min(value = 0, message = "Invalid difficulty")
    @Max(value = 2, message = "Invalid difficulty")
    private DifficultyEnum difficulty;

    @Min(value = 0, message = "Invalid exercise category")
    @Max(value = 3, message = "Invalid exercise category")
    private ExerciseCategoryEnum category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_id", referencedColumnName = "id")
    private Equipment equipment;

    @JsonIgnore
    @ManyToMany(mappedBy = "exercises")
    private Set<Training> trainings = new HashSet<>();

    public Exercise(long date, String name, DifficultyEnum difficulty, ExerciseCategoryEnum category, Equipment equipment) {
        super(date, name);
        this.difficulty = difficulty;
        this.category = category;
        this.equipment = equipment;

    }

    public Exercise() {

    }

    public void occupyEquipment() {
        equipment.setOccupied(true);
    }

    public void freeEquipment() {
        equipment.setOccupied(false);
    }

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


}
