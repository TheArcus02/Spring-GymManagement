package com.mike.gymmanagement.service;

import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.Client;
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
        return trainerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Trainer not found with id: " + id));
    }

    public Trainer addTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public Trainer updateTrainer(Long id, Trainer updatedTrainer) {
        Optional<Trainer> optionalTrainer = trainerRepository.findById(id);

        if (optionalTrainer.isPresent()) {
            Trainer existingTrainer = optionalTrainer.get();

            existingTrainer.setName(updatedTrainer.getName());
            existingTrainer.setDate(updatedTrainer.getDate());
            existingTrainer.setSurname(updatedTrainer.getSurname());
            existingTrainer.setSalary(updatedTrainer.getSalary());

            return trainerRepository.save(existingTrainer);
        }

        throw new NotFoundException("Trainer not found with id: " + id);
    }

    public void deleteTrainer(Long id) {
        Trainer existingTrainer = trainerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Trainer not found with id: " + id));
        existingTrainer.getClients().forEach(client -> client.setTrainer(null));

        trainerRepository.save(existingTrainer);
        trainerRepository.deleteById(id);
    }
}
