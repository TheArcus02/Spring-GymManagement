package com.mike.gymmanagement.controller;

import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.Client;
import com.mike.gymmanagement.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Client>> getAllClients() {
        return ResponseEntity.ok().body(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(client);
    }

    @PostMapping("")
    public Client addClient(@RequestBody @Valid Client client) {
        return clientService.addClient(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id,
                                               @RequestBody @Valid Client updatedClient) throws NotFoundException {
        Client client = clientService.updateClient(id, updatedClient);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(client);
    }

    @PutMapping("/{clientId}/trainer/{trainerId}")
    public ResponseEntity<Client> assignTrainer(@PathVariable Long clientId,
                                                @PathVariable Long trainerId) throws NotFoundException {
        Client client = clientService.assignTrainer(clientId, trainerId);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(client);
    }

    @PutMapping("/{clientId}/trainer")
    public ResponseEntity<Client> removeTrainer(@PathVariable Long clientId) throws NotFoundException {
        Client client = clientService.removeTrainer(clientId);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(client);
    }

    @PutMapping("/{clientId}/workout-plan/{workoutPlanId}")
    public ResponseEntity<Client> assignWorkoutPlan(@PathVariable Long clientId,
                                                    @PathVariable Long workoutPlanId) throws NotFoundException {
        Client client = clientService.assignWorkoutPlan(clientId, workoutPlanId);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(client);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) throws NotFoundException {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
