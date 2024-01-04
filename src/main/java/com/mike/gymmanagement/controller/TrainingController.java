package com.mike.gymmanagement.controller;

import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.Training;
import com.mike.gymmanagement.service.TrainingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/training")
public class TrainingController {

    private final TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Training>> getAllTrainings() {
        return ResponseEntity.ok(trainingService.getAllTrainings());
    }

    @GetMapping("{id}")
    public ResponseEntity<Training> getTrainingById(@PathVariable Long id) throws NotFoundException {
        Training training = trainingService.getTrainingById(id);
        if (training == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trainingService.getTrainingById(id));
    }

    @PostMapping("")
    public ResponseEntity<Training> addTraining(@RequestBody @Valid Training training) {
        return ResponseEntity.ok(trainingService.addTraining(training));
    }

    @PutMapping("{id}")
    public ResponseEntity<Training> updateTraining(@PathVariable Long id,
                                                   @RequestBody @Valid Training updatedTraining) throws NotFoundException {
        return ResponseEntity.ok(trainingService.updateTraining(id, updatedTraining));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) throws NotFoundException {
        trainingService.deleteTraining(id);
        return ResponseEntity.noContent().build();
    }

}
