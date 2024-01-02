package com.mike.gymmanagement.service;

import com.mike.gymmanagement.exception.InvalidUpdateException;
import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.CardioExercise;
import com.mike.gymmanagement.model.Exercise;
import com.mike.gymmanagement.model.StrengthExercise;
import com.mike.gymmanagement.repository.CardioExerciseRepository;
import com.mike.gymmanagement.repository.ExerciseRepository;
import com.mike.gymmanagement.repository.StrengthExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    private final ExerciseRepository<Exercise> exerciseRepository;
    private final StrengthExerciseRepository strengthExerciseRepository;
    private final CardioExerciseRepository cardioExerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository<Exercise> exerciseRepository, StrengthExerciseRepository strengthExerciseRepository, CardioExerciseRepository cardioExerciseRepository) {
        this.exerciseRepository = exerciseRepository;
        this.strengthExerciseRepository = strengthExerciseRepository;
        this.cardioExerciseRepository = cardioExerciseRepository;
    }

    public Iterable<Exercise> getAllExercises() {
        return exerciseRepository.findAllExercises();
    }

    public Iterable<StrengthExercise> getAllStrengthExercises() {
        return strengthExerciseRepository.findAll();
    }

    public StrengthExercise getStrengthExerciseById(long id) {
        return strengthExerciseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invalid exercise Id:" + id));
    }

    public Iterable<CardioExercise> getAllCardioExercises() {
        return cardioExerciseRepository.findAll();
    }

    public CardioExercise getCardioExerciseById(long id) {
        return cardioExerciseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invalid exercise Id:" + id));
    }

    public Exercise getExerciseById(long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invalid exercise Id:" + id));
    }

    public Exercise saveExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Exercise updateExercise(long id, Exercise exercise) {
        Exercise existingExercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invalid exercise Id:" + id));
        switch (existingExercise.getType()) {
            case "StrengthExercise":
                if (exercise instanceof StrengthExercise) {
                    return exerciseRepository.save(updateStrengthExercise((StrengthExercise) existingExercise, (StrengthExercise) exercise));
                }
                throw new InvalidUpdateException("Invalid update for StrengthExercise type");
            case "CardioExercise":
                if (exercise instanceof CardioExercise) {
                    return exerciseRepository.save(updateCardioExercise((CardioExercise) existingExercise, (CardioExercise) exercise));
                }
                throw new InvalidUpdateException("Invalid update for CardioExercise type");
            default:
                throw new InvalidUpdateException("Unknown exercise type for exercise Id:" + id);
        }

    }

    public void deleteExercise(long id) {
        if (!exerciseRepository.existsById(id)) {
            throw new NotFoundException("Exercise not found with id: " + id);
        }
        exerciseRepository.deleteById(id);
    }

    private static CardioExercise updateCardioExercise(CardioExercise existingExercise, CardioExercise exercise) {
        existingExercise.setName(exercise.getName());
        existingExercise.setCategory(exercise.getCategory());
        existingExercise.setDifficulty(exercise.getDifficulty());
        existingExercise.setDuration(exercise.getDuration());
        existingExercise.setTempo(exercise.getTempo());
        return existingExercise;
    }

    private static StrengthExercise updateStrengthExercise(StrengthExercise existingExercise, StrengthExercise exercise) {
        existingExercise.setName(exercise.getName());
        existingExercise.setCategory(exercise.getCategory());
        existingExercise.setDifficulty(exercise.getDifficulty());
        existingExercise.setReps(exercise.getReps());
        existingExercise.setSets(exercise.getSets());
        existingExercise.setWeight(exercise.getWeight());
        return existingExercise;
    }


}
