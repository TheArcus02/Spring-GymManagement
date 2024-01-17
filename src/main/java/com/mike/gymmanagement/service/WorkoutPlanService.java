package com.mike.gymmanagement.service;

import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.Client;
import com.mike.gymmanagement.model.Training;
import com.mike.gymmanagement.model.WorkoutPlan;
import com.mike.gymmanagement.repository.ClientRepository;
import com.mike.gymmanagement.repository.TrainingRepository;
import com.mike.gymmanagement.repository.WorkoutPlanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;

    private final TrainingRepository trainingRepository;

    @Autowired
    public WorkoutPlanService(WorkoutPlanRepository workoutPlanRepository, TrainingRepository trainingRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
        this.trainingRepository = trainingRepository;
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
        Optional<WorkoutPlan> optionalWorkoutPlan = workoutPlanRepository.findById(id);

        if (optionalWorkoutPlan.isPresent()) {
            WorkoutPlan existingWorkoutPlan = optionalWorkoutPlan.get();

            existingWorkoutPlan.setName(updatedWorkoutPlan.getName());
            existingWorkoutPlan.setDate(updatedWorkoutPlan.getDate());
            existingWorkoutPlan.setDescription(updatedWorkoutPlan.getDescription());
            existingWorkoutPlan.setDifficulty(updatedWorkoutPlan.getDifficulty());

            return workoutPlanRepository.save(existingWorkoutPlan);
        }

        throw new NotFoundException("Client not found with id: " + id);
    }

    public WorkoutPlan assignTraining(Long workoutPlanId, Long trainingId) {
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(workoutPlanId)
                .orElseThrow(() -> new NotFoundException("WorkoutPlan not found with id: " + workoutPlanId));

        Training training = trainingRepository.findById(trainingId).orElseThrow(() -> new NotFoundException("Training not found with id: " + trainingId));

        workoutPlan.addTraining(training);

        return workoutPlanRepository.save(workoutPlan);
    }

    @Transactional
    public WorkoutPlan unassignTraining(Long workoutPlanId, Long trainingId) {
        WorkoutPlan workoutPlan = workoutPlanRepository.findById(workoutPlanId)
                .orElseThrow(() -> new NotFoundException("WorkoutPlan not found with id: " + workoutPlanId));

        Training training = trainingRepository.findById(trainingId).orElseThrow(() -> new NotFoundException("Training not found with id: " + trainingId));

        workoutPlan.removeTraining(training);
        training.removeWorkoutPlan(workoutPlan);

        workoutPlanRepository.save(workoutPlan);
        trainingRepository.save(training);

        return workoutPlan;
    }

    public void deleteWorkoutPlan(Long workoutPlanId) {
        WorkoutPlan existingWorkoutPlan = workoutPlanRepository.findById(workoutPlanId).
                orElseThrow(() -> new NotFoundException("WorkoutPlan not found with id: " + workoutPlanId));

        existingWorkoutPlan.getClients().forEach(Client::removeWorkoutPlan);
        existingWorkoutPlan.getTrainings().forEach(training -> training.removeWorkoutPlan(existingWorkoutPlan));

        workoutPlanRepository.save(existingWorkoutPlan);

        workoutPlanRepository.deleteById(workoutPlanId);
    }

}
