package com.mike.gymmanagement.controller;

import com.mike.gymmanagement.model.Client;
import com.mike.gymmanagement.model.Trainer;
import com.mike.gymmanagement.model.WorkoutPlan;
import com.mike.gymmanagement.repository.ClientRepository;
import com.mike.gymmanagement.repository.TrainerRepository;
import com.mike.gymmanagement.repository.WorkoutPlanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

    private final TrainerRepository trainerRepository;


    @Autowired
    public TrainerController(TrainerRepository trainerRepository, ClientRepository clientRepository, WorkoutPlanRepository workoutPlanRepository) {
        this.trainerRepository = trainerRepository;
    }

    @GetMapping("/")
    public Iterable<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Long id) {
        return trainerRepository.findById(id)
                .map(trainer -> ResponseEntity.ok().body(trainer))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Trainer> addTrainer(@RequestBody Trainer trainer) {
        Trainer newTrainer = trainerRepository.save(trainer);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTrainer);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Trainer> updateTrainer(@PathVariable Long id, @RequestBody Trainer updatedTrainer) {
        return trainerRepository.findById(id)
                .map(existingTrainer -> {
                    BeanUtils.copyProperties(updatedTrainer, existingTrainer, "id");
                    Trainer savedTrainer = trainerRepository.save(existingTrainer);
                    return ResponseEntity.ok().body(savedTrainer);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        if (trainerRepository.existsById(id)) {
            trainerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

//    @PostMapping("/{trainerId}/assign/{clientId}")
//    public ResponseEntity<Client> assignWorkoutPlan(@PathVariable Integer trainerId,
//                                                    @PathVariable Integer clientId,
//                                                    @RequestBody WorkoutPlan workoutPlan) {
//        return trainerRepository.findById(trainerId)
//                .map(trainer -> {
//                    Optional<Client> clientOptional = trainer.getClients().stream()
//                            .filter(client -> client.getId() == clientId)
//                            .findFirst();
//
//                    return clientOptional.map(client -> {
//                        trainer.assignWorkoutPlan(client, workoutPlan);
//                        trainerRepository.save(trainer);
//                        return ResponseEntity.ok(client);
//                    }).orElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }

}
