package com.mike.gymmanagement.service;

import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.Training;
import com.mike.gymmanagement.repository.TrainerRepository;
import com.mike.gymmanagement.repository.TrainingRepository;
import com.mike.gymmanagement.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository, WorkoutPlanRepository workoutPlanRepository) {
        this.trainingRepository = trainingRepository;
        this.workoutPlanRepository = workoutPlanRepository;
    }

    public Iterable<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    public Training getTrainingById(Long id) {
        return trainingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Training not found with id: " + id));
    }

    public Training addTraining(Training training) {
        return trainingRepository.save(training);
    }

    public Training updateTraining(Long id, Training updatedTraining) {
        Optional<Training> optionalTraining = trainingRepository.findById(id);

        if (optionalTraining.isPresent()) {
            Training existingTraining = optionalTraining.get();

            existingTraining.setName(updatedTraining.getName());
            existingTraining.setDescription(updatedTraining.getDescription());

            return trainingRepository.save(existingTraining);
        }

        throw new NotFoundException("Training not found with id: " + id);
    }

    public void deleteTraining(Long id) {
        if (!workoutPlanRepository.existsById(id)) {
            throw new NotFoundException("Training not found with id: " + id);
        }
        trainingRepository.deleteById(id);
    }
}
