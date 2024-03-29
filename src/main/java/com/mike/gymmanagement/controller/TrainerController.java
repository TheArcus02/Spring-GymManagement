package com.mike.gymmanagement.controller;


import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.Trainer;
import com.mike.gymmanagement.service.TrainerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

    TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Trainer>> getAllTrainers() {
        return ResponseEntity.ok(trainerService.getAllTrainers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Long id) throws NotFoundException {
        Trainer trainer = trainerService.getTrainerById(id);
        if (trainer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(trainer);
    }

    @PostMapping("")
    public ResponseEntity<Trainer> addTrainer(@RequestBody @Valid Trainer trainer) {
        Trainer savedTrainer = trainerService.addTrainer(trainer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTrainer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trainer> updateTrainer(@PathVariable Long id,
                                                 @RequestBody @Valid Trainer updatedTrainer) throws NotFoundException {
        Trainer trainer = trainerService.updateTrainer(id, updatedTrainer);
        if (trainer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(trainer);
    }

    @PatchMapping("/{trainerId}/client/{clientId}")
    public ResponseEntity<Trainer> assignClient(@PathVariable Long trainerId,
                                                @PathVariable Long clientId) throws NotFoundException {
        Trainer trainer = trainerService.assignClient(trainerId, clientId);
        if (trainer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(trainer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) throws NotFoundException {
        trainerService.deleteTrainer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Trainer>> searchTrainers(@RequestParam String name, @RequestParam String surname) {
        return ResponseEntity.ok(trainerService.getTrainersByNameOrSurname(name, surname));
    }

}
