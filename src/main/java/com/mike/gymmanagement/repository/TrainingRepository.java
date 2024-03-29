package com.mike.gymmanagement.repository;

import com.mike.gymmanagement.model.Training;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends CrudRepository<Training, Long>, SearchableRepository<Training> {

}