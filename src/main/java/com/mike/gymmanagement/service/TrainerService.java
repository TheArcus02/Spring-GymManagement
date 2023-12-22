package com.mike.gymmanagement.service;

import com.mike.gymmanagement.model.Trainer;
import com.mike.gymmanagement.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Iterable<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    public Trainer getTrainerById(Long id) {
        return trainerRepository.findById(id).orElse(null);
    }

    public Trainer addTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public Trainer updateTrainer(Long id, Trainer updatedTrainer) {
        return trainerRepository.findById(id)
                .map(existingTrainer -> {
                    existingTrainer.setName(Optional.ofNullable(updatedTrainer.getName()).orElse(existingTrainer.getName()));
                    existingTrainer.setSurname(Optional.ofNullable(updatedTrainer.getSurname()).orElse(existingTrainer.getSurname()));

                    double updatedSalary = updatedTrainer.getSalary();
                    if (updatedSalary != 0.0 && updatedSalary >= 0.0) {
                        existingTrainer.setSalary(updatedSalary);
                    }
                    return trainerRepository.save(existingTrainer);
                })
                .orElse(null);
    }

    public void deleteTrainer(Long id) {
        if (trainerRepository.existsById(id)) {
            trainerRepository.deleteById(id);
        }
    }
}
