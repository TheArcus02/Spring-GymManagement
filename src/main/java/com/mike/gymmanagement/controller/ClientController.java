package com.mike.gymmanagement.controller;

import com.mike.gymmanagement.model.Client;
import com.mike.gymmanagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        Client client = clientService.updateClient(id, updatedClient);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(client);
    }

    @PutMapping("/{clientId}/trainer/{trainerId}")
    public ResponseEntity<Client> assignTrainer(@PathVariable Long clientId, @PathVariable Long trainerId) {
        Client client = clientService.assignTrainer(clientId, trainerId);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(client);
    }

    @PostMapping("")
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
