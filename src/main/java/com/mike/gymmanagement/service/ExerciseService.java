package com.mike.gymmanagement.service;

import com.mike.gymmanagement.exception.InvalidUpdateException;
import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.CardioExercise;
import com.mike.gymmanagement.model.Equipment;
import com.mike.gymmanagement.model.Exercise;
import com.mike.gymmanagement.model.StrengthExercise;
import com.mike.gymmanagement.repository.CardioExerciseRepository;
import com.mike.gymmanagement.repository.EquipmentRepository;
import com.mike.gymmanagement.repository.ExerciseRepository;
import com.mike.gymmanagement.repository.StrengthExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    private final ExerciseRepository<Exercise> exerciseRepository;
    private final StrengthExerciseRepository strengthExerciseRepository;
    private final CardioExerciseRepository cardioExerciseRepository;

    private final EquipmentRepository<Equipment> equipmentRepository;

    @Autowired
    public ExerciseService(ExerciseRepository<Exercise> exerciseRepository, StrengthExerciseRepository strengthExerciseRepository, CardioExerciseRepository cardioExerciseRepository, EquipmentRepository<Equipment> equipmentRepository) {
        this.exerciseRepository = exerciseRepository;
        this.strengthExerciseRepository = strengthExerciseRepository;
        this.cardioExerciseRepository = cardioExerciseRepository;
        this.equipmentRepository = equipmentRepository;
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

    public Exercise assignEquipment(long exerciseId, long equipmentId) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NotFoundException("Exercise not found with id: " + exerciseId));
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new NotFoundException("Equipment not found with id: " + equipmentId));

        if (exercise.getEquipment() != null) {
            exercise.freeEquipment();
        }

        exercise.setEquipment(equipment);
        return exerciseRepository.save(exercise);
    }

    public void deleteExercise(long id) {
        Exercise existingExercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Exercise not found with id: " + id));

        existingExercise.setEquipment(null);

        exerciseRepository.save(existingExercise);

        exerciseRepository.deleteById(id);
    }

    public Iterable<Exercise> findExercisesByName(String name) {
        return exerciseRepository.findByNameContainingIgnoreCase(name);
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
