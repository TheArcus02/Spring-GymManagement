package com.mike.gymmanagement.controller;

import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.CardioExercise;
import com.mike.gymmanagement.model.Exercise;
import com.mike.gymmanagement.model.StrengthExercise;
import com.mike.gymmanagement.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("")
    public Iterable<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @GetMapping("/{id}")
    public Exercise getExerciseById(@PathVariable long id) throws NotFoundException {
        return exerciseService.getExerciseById(id);
    }

    @GetMapping("/strength")
    public Iterable<StrengthExercise> getAllStrengthExercises() {
        return exerciseService.getAllStrengthExercises();
    }

    @GetMapping("/strength/{id}")
    public StrengthExercise getStrengthExerciseById(@PathVariable long id) throws NotFoundException {
        return exerciseService.getStrengthExerciseById(id);
    }

    @GetMapping("/cardio")
    public Iterable<CardioExercise> getAllCardioExercises() {
        return exerciseService.getAllCardioExercises();
    }

    @GetMapping("/cardio/{id}")
    public CardioExercise getCardioExerciseById(@PathVariable long id) throws NotFoundException {
        return exerciseService.getCardioExerciseById(id);
    }

    @PostMapping("")
    public Exercise saveExercise(@RequestBody @Valid Exercise exercise) {
        return exerciseService.saveExercise(exercise);
    }

    @PutMapping("/{id}")
    public Exercise updateExercise(@PathVariable long id, @RequestBody @Valid Exercise exercise) throws NotFoundException, MethodArgumentNotValidException {
        return exerciseService.updateExercise(id, exercise);
    }

}
