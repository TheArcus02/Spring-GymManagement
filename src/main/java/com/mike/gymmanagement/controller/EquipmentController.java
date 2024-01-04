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
    public Iterable<Equipment> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @GetMapping("/{id}")
    public Equipment getEquipmentById(@PathVariable long id) throws NotFoundException {
        return equipmentService.getEquipmentById(id);
    }

    @PostMapping("")
    public Equipment saveEquipment(@RequestBody @Valid Equipment equipment) {
        return equipmentService.saveEquipment(equipment);
    }

    @PutMapping("/{id}")
    public Equipment updateEquipment(@PathVariable long id, @RequestBody @Valid Equipment equipment) throws NotFoundException, InvalidUpdateException {
        return equipmentService.updateEquipment(id, equipment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable long id) throws NotFoundException {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }
}
