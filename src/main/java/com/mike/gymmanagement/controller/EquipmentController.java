package com.mike.gymmanagement.controller;

import com.mike.gymmanagement.exception.InvalidUpdateException;
import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.Equipment;
import com.mike.gymmanagement.service.EquipmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Equipment>> getAllEquipment() {
        return ResponseEntity.ok(equipmentService.getAllEquipment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable long id) throws NotFoundException {
        Equipment equipment = equipmentService.getEquipmentById(id);
        if (equipment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(equipment);
    }

    @PostMapping("")
    public ResponseEntity<Equipment> saveEquipment(@RequestBody @Valid Equipment equipment) {
        return ResponseEntity.ok(equipmentService.saveEquipment(equipment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable long id, @RequestBody @Valid Equipment equipment) throws NotFoundException, InvalidUpdateException {
        return ResponseEntity.ok(equipmentService.updateEquipment(id, equipment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable long id) throws NotFoundException {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Equipment>> searchEquipment(@RequestParam String name) {
        return ResponseEntity.ok(equipmentService.findEquipmentByName(name));
    }
}
