package com.mike.gymmanagement.repository;

import com.mike.gymmanagement.model.Exercise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository<T extends Exercise> extends CrudRepository<T, Long>, SearchableRepository<T> {
    @Query("SELECT e FROM Exercise e WHERE TYPE(e) IN (StrengthExercise , CardioExercise)")
    List<Exercise> findAllExercises();

}