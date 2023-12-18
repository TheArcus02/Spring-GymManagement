package com.mike.gymmanagement.repository;

import com.mike.gymmanagement.model.Exercise;
import com.mike.gymmanagement.model.Trainer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

}