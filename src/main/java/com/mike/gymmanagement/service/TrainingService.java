package com.mike.gymmanagement.service;

import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.Exercise;
import com.mike.gymmanagement.model.Training;
import com.mike.gymmanagement.repository.ExerciseRepository;
import com.mike.gymmanagement.repository.TrainingRepository;
import com.mike.gymmanagement.repository.WorkoutPlanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final ExerciseRepository<Exercise> exerciseRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository, ExerciseRepository<Exercise> exerciseRepository) {
        this.trainingRepository = trainingRepository;
        this.exerciseRepository = exerciseRepository;
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

    public Training assignExercise(Long trainingId, Long exerciseId) {
        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new NotFoundException("Training not found with id: " + trainingId));

        Exercise exercise = exerciseRepository.findById(exerciseId).
                orElseThrow(() -> new NotFoundException("Exercise not found with id: " + exerciseId));

        training.addExercise(exercise);

        return trainingRepository.save(training);
    }

    @Transactional
    public Training unassignExercise(Long trainingId, Long exerciseId) {


        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new NotFoundException("Training not found with id: " + trainingId));

        Exercise exercise = exerciseRepository.findById(exerciseId).
                orElseThrow(() -> new NotFoundException("Exercise not found with id: " + exerciseId));

        exercise.removeTraining(training);

        training.removeExercise(exercise);


        trainingRepository.save(training);
        exerciseRepository.save(exercise);

        return training;

    }

    public void deleteTraining(Long id) {
        if (!trainingRepository.existsById(id)) {
            throw new NotFoundException("Training not found with id: " + id);
        }
        trainingRepository.deleteById(id);
    }

    public Iterable<Training> findTrainingsByName(String name) {
        return trainingRepository.findByNameContainingIgnoreCase(name);
    }
}
