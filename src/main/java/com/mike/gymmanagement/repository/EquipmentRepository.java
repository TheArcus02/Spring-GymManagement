package com.mike.gymmanagement.repository;

import com.mike.gymmanagement.model.Equipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository<T extends Equipment> extends CrudRepository<T, Long>, SearchableRepository<T> {
    @Query("SELECT e FROM Equipment e WHERE TYPE(e) IN (Dumbells , Barbell, Machine)")
    List<Equipment> findAllEquipment();
}