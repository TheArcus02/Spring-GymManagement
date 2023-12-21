package com.mike.gymmanagement.controller;

import com.mike.gymmanagement.model.Client;
import com.mike.gymmanagement.repository.ClientRepository;
import com.mike.gymmanagement.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientRepository clientRepository;
    private final TrainerRepository trainerRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository, TrainerRepository trainerRepository) {
        this.clientRepository = clientRepository;
        this.trainerRepository = trainerRepository;
    }

    @GetMapping("")
    public Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @PutMapping("/{clientId}/trainer/{trainerId}")
    public ResponseEntity<Client> assignTrainer(@PathVariable Long clientId, @PathVariable Long trainerId) {
        return clientRepository.findById(clientId)
                .map(client -> trainerRepository.findById(trainerId)
                        .map(trainer -> {
                            client.setTrainer(trainer);
                            Client savedClient = clientRepository.save(client);
                            return ResponseEntity.ok().body(savedClient);
                        })
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public Client addClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }
}
