package com.mike.gymmanagement.service;

import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.Client;
import com.mike.gymmanagement.model.Exercise;
import com.mike.gymmanagement.model.Trainer;
import com.mike.gymmanagement.repository.ClientRepository;
import com.mike.gymmanagement.repository.ExerciseRepository;
import com.mike.gymmanagement.repository.TrainerRepository;
import com.mike.gymmanagement.repository.WorkoutPlanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final TrainerRepository trainerRepository;
    private final WorkoutPlanRepository workoutPlanRepository;

    private final ExerciseRepository<Exercise> exerciseRepository;


    @Autowired
    public ClientService(ClientRepository clientRepository, TrainerRepository trainerRepository, WorkoutPlanRepository workoutPlanRepository, ExerciseRepository<Exercise> exerciseRepository) {
        this.clientRepository = clientRepository;
        this.trainerRepository = trainerRepository;
        this.workoutPlanRepository = workoutPlanRepository;
        this.exerciseRepository = exerciseRepository;
    }


    public Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Client not found with id: " + id));
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public Client assignTrainer(Long clientId, Long trainerId) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + clientId));
        Trainer existingTrainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new NotFoundException("Trainer not found with id: " + trainerId));

        existingClient.assignTrainer(existingTrainer);
        return clientRepository.save(existingClient);
    }

    public Client removeTrainer(Long clientId) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + clientId));

        existingClient.setTrainer(null);
        return clientRepository.save(existingClient);
    }

    public Client assignWorkoutPlan(Long clientId, Long workoutPlanId) {
        return clientRepository.findById(clientId)
                .flatMap(client -> workoutPlanRepository.findById(workoutPlanId)
                        .map(workoutPlan -> {
                            client.setWorkoutPlan(workoutPlan);
                            return clientRepository.save(client);
                        }))
                .orElseThrow(() -> new NotFoundException("Client or workout plan not found"));
    }


    public Client updateClient(Long id, Client updatedClient) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            Client existingClient = optionalClient.get();

            existingClient.setName(updatedClient.getName());
            existingClient.setSurname(updatedClient.getSurname());
            existingClient.setWeight(updatedClient.getWeight());

            return clientRepository.save(existingClient);
        }

        throw new NotFoundException("Client not found with id: " + id);
    }

    public void deleteClient(Long id) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + id));

        existingClient.setTrainer(null);
        clientRepository.save(existingClient);

        clientRepository.deleteById(id);
    }

    public Iterable<Client> findClientsByNameOrSurname(String name, String surname) {
        return clientRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(name, surname);
    }

    @Transactional
    public Client trainClient(Long clientId, Long exerciseId) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + clientId));

        Exercise existingExercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NotFoundException("Exercise not found with id: " + exerciseId));

//        if (existingClient.getWorkoutPlan() == null) {
//            throw new IllegalStateException("Client does not have a workout plan");
//        }

        if (existingClient.getIsTraining()) {
            Exercise currentExercise = exerciseRepository.findById(existingClient.getCurrentlyTrainedExerciseId())
                    .orElseThrow(() -> new NotFoundException("Exercise not found with id: " + existingClient.getCurrentlyTrainedExerciseId()));
            existingClient.stopTraining(currentExercise);
        }

        existingClient.startTraining(existingExercise);
        return clientRepository.save(existingClient);
    }

    @Transactional
    public Client stopTrainingClient(Long clientId, Long exerciseId) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + clientId));

        Exercise existingExercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NotFoundException("Exercise not found with id: " + exerciseId));

        existingClient.stopTraining(existingExercise);

        return clientRepository.save(existingClient);
    }
}
