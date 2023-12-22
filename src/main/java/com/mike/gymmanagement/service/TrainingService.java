package com.mike.gymmanagement.service;

import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.Training;
import com.mike.gymmanagement.repository.TrainerRepository;
import com.mike.gymmanagement.repository.TrainingRepository;
import com.mike.gymmanagement.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return trainingRepository.findById(id)
                .map(training -> {
                    if (updatedTraining.getName() != null) {
                        training.setName(updatedTraining.getName());
                    }
                    return trainingRepository.save(training);
                })
                .orElse(null);
    }

    public void deleteTraining(Long id) {
        trainingRepository.deleteById(id);
    }
}
