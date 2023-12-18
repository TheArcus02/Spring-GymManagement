package com.mike.gymmanagement.repository;

import com.mike.gymmanagement.model.WorkoutPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutPlanRepository extends CrudRepository<WorkoutPlan, Long> {

}