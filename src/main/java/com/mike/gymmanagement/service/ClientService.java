package com.mike.gymmanagement.service;

import com.mike.gymmanagement.model.Client;
import com.mike.gymmanagement.repository.ClientRepository;
import com.mike.gymmanagement.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final TrainerRepository trainerRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, TrainerRepository trainerRepository) {
        this.clientRepository = clientRepository;
        this.trainerRepository = trainerRepository;
    }

    public Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public Client assignTrainer(Long clientId, Long trainerId) {
        return clientRepository.findById(clientId)
                .flatMap(client -> trainerRepository.findById(trainerId)
                        .map(trainer -> {
                            client.setTrainer(trainer);
                            return clientRepository.save(client);
                        }))
                .orElse(null);
    }


    public Client updateClient(Long id, Client updatedClient) {
        return clientRepository.findById(id)
                .map(client -> {
                    if (updatedClient.getName() != null) {
                        client.setName(updatedClient.getName());
                    }
                    if (updatedClient.getSurname() != null) {
                        client.setSurname(updatedClient.getSurname());
                    }
                    if (updatedClient.getWeight() != 0.0) {
                        if (updatedClient.getWeight() < 0.0) {
                            throw new IllegalArgumentException("Weight cannot be negative");
                        }
                        client.setWeight(updatedClient.getWeight());
                    }
                    if (updatedClient.getEmail() != null) {
                        client.setEmail(updatedClient.getEmail());
                    }
                    if (updatedClient.getWorkoutPlan() != null) {
                        client.setWorkoutPlan(updatedClient.getWorkoutPlan());
                    }
                    return clientRepository.save(client);
                })
                .orElse(null);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

}
