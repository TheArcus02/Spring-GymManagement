package com.mike.gymmanagement.service;

import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.Training;
import com.mike.gymmanagement.model.WorkoutPlan;
import com.mike.gymmanagement.repository.ClientRepository;
import com.mike.gymmanagement.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final ClientRepository clientRepository;
    private final TrainingService trainingService;

    @Autowired
    public WorkoutPlanService(WorkoutPlanRepository workoutPlanRepository, ClientRepository clientRepository, TrainingService trainingService) {
        this.workoutPlanRepository = workoutPlanRepository;
        this.clientRepository = clientRepository;
        this.trainingService = trainingService;
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

    public WorkoutPlan assignTraining(Long workoutPlanId, Long trainingId) {
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(workoutPlanId)
                .orElseThrow(() -> new NotFoundException("WorkoutPlan not found with id: " + workoutPlanId));

        try {
            Training training = trainingService.getTrainingById(trainingId);
            workoutPlan.addTraining(training);
            return workoutPlanRepository.save(workoutPlan);
        } catch (NotFoundException e) {
            throw new NotFoundException("Training not found with id: " + trainingId);
        }
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
