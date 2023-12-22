package com.mike.gymmanagement.service;

import com.mike.gymmanagement.model.WorkoutPlan;
import com.mike.gymmanagement.repository.ClientRepository;
import com.mike.gymmanagement.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public WorkoutPlanService(WorkoutPlanRepository workoutPlanRepository, ClientRepository clientRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
        this.clientRepository = clientRepository;
    }

    public Iterable<WorkoutPlan> getAllWorkoutPlans() {
        return workoutPlanRepository.findAll();
    }

    public WorkoutPlan getWorkoutPlanById(Long id) {
        return workoutPlanRepository.findById(id).orElse(null);
    }

    public WorkoutPlan addWorkoutPlan(WorkoutPlan workoutPlan) {
        return workoutPlanRepository.save(workoutPlan);
    }

    public WorkoutPlan updateWorkoutPlan(Long id, WorkoutPlan updatedWorkoutPlan) {
        return workoutPlanRepository.findById(id)
                .map(workoutPlan -> {
                    if (updatedWorkoutPlan.getName() != null) {
                        workoutPlan.setName(updatedWorkoutPlan.getName());
                    }
                    if (updatedWorkoutPlan.getDescription() != null) {
                        workoutPlan.setDescription(updatedWorkoutPlan.getDescription());
                    }
                    if (updatedWorkoutPlan.getDifficulty() != null) {
                        workoutPlan.setDifficulty(updatedWorkoutPlan.getDifficulty());
                    }
                    if (updatedWorkoutPlan.getTrainings() != null) {
                        workoutPlan.setTrainings(updatedWorkoutPlan.getTrainings());
                    }
                    return workoutPlanRepository.save(workoutPlan);
                })
                .orElse(null);
    }

    public void deleteWorkoutPlan(Long workoutPlanId) {
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(workoutPlanId).orElse(null);
        if (workoutPlan != null) {
            workoutPlan.getClients().forEach(client -> {
                client.setWorkoutPlan(null);
                clientRepository.save(client);
            });

            workoutPlanRepository.deleteById(workoutPlanId);
        }
    }

}
