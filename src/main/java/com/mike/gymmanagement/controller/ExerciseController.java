package com.mike.gymmanagement.controller;

import com.mike.gymmanagement.exception.InvalidUpdateException;
import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.CardioExercise;
import com.mike.gymmanagement.model.Exercise;
import com.mike.gymmanagement.model.StrengthExercise;
import com.mike.gymmanagement.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
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
    public ResponseEntity<StrengthExercise> getStrengthExerciseById(@PathVariable long id) throws NotFoundException {
        return ResponseEntity.ok(exerciseService.getStrengthExerciseById(id));
    }

    @GetMapping("/cardio")
    public ResponseEntity<Iterable<CardioExercise>> getAllCardioExercises() {
        return ResponseEntity.ok(exerciseService.getAllCardioExercises());
    }

    @GetMapping("/cardio/{id}")
    public ResponseEntity<CardioExercise> getCardioExerciseById(@PathVariable long id) throws NotFoundException {
        return ResponseEntity.ok(exerciseService.getCardioExerciseById(id));
    }

    @PostMapping("")
    public ResponseEntity<Exercise> saveExercise(@RequestBody @Valid Exercise exercise) {
        return ResponseEntity.ok(exerciseService.saveExercise(exercise));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable long id, @RequestBody @Valid Exercise exercise) throws NotFoundException, InvalidUpdateException {
        return ResponseEntity.ok(exerciseService.updateExercise(id, exercise));
    }

    @PatchMapping("/{exerciseId}/equipment/{equipmentId}")
    public ResponseEntity<Exercise> assignEquipment(@PathVariable long exerciseId, @PathVariable long equipmentId) throws NotFoundException {
        return ResponseEntity.ok(exerciseService.assignEquipment(exerciseId, equipmentId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable long id) throws NotFoundException {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Exercise>> searchExercises(@RequestParam String name) {
        return ResponseEntity.ok(exerciseService.findExercisesByName(name));
    }

}
