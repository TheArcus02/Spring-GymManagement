package com.mike.gymmanagement.model;

import com.mike.gymmanagement.enums.DifficultyEnum;
import com.mike.gymmanagement.enums.ExerciseCategoryEnum;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Exercise extends DbObject {
    private DifficultyEnum difficulty;
    private ExerciseCategoryEnum category;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    public Exercise(int id, long date, String name, DifficultyEnum difficulty, ExerciseCategoryEnum category, Equipment equipment) {
        super(id, date, name);
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
