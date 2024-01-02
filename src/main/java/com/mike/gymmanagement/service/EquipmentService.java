package com.mike.gymmanagement.service;

import com.mike.gymmanagement.exception.InvalidUpdateException;
import com.mike.gymmanagement.exception.NotFoundException;
import com.mike.gymmanagement.model.Barbell;
import com.mike.gymmanagement.model.Dumbells;
import com.mike.gymmanagement.model.Equipment;
import com.mike.gymmanagement.model.Machine;
import com.mike.gymmanagement.repository.BarbellRepository;
import com.mike.gymmanagement.repository.DumbellsRepository;
import com.mike.gymmanagement.repository.EquipmentRepository;
import com.mike.gymmanagement.repository.MachineRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {

    private final EquipmentRepository<Equipment> equipmentRepository;
    private final DumbellsRepository dumbellsRepository;
    private final BarbellRepository barbellRepository;
    private final MachineRepository machineRepository;


    public EquipmentService(EquipmentRepository<Equipment> equipmentRepository, DumbellsRepository dumbellsRepository, BarbellRepository barbellRepository, MachineRepository machineRepository) {
        this.equipmentRepository = equipmentRepository;
        this.dumbellsRepository = dumbellsRepository;
        this.barbellRepository = barbellRepository;
        this.machineRepository = machineRepository;
    }

    public Iterable<Equipment> getAllEquipment() {
        return equipmentRepository.findAllEquipment();
    }

    public Equipment getEquipmentById(long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invalid equipment Id:" + id));
    }

    public Equipment saveEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(long id, Equipment equipment) {
        Equipment existingEquipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invalid equipment Id:" + id));

        switch (existingEquipment.getType()) {
            case "Dumbells":
                if (equipment instanceof Dumbells) {
                    return dumbellsRepository.save(updateDumbells((Dumbells) existingEquipment, (Dumbells) equipment));
                }
                throw new InvalidUpdateException("Invalid update for equipment Id:" + id);
            case "Barbell":
                if (equipment instanceof Barbell) {
                    return barbellRepository.save(updateBarbell((Barbell) existingEquipment, (Barbell) equipment));
                }
                throw new InvalidUpdateException("Invalid update for equipment Id:" + id);
            case "Machine":
                if (equipment instanceof Machine) {
                    return machineRepository.save(updateMachine((Machine) existingEquipment, (Machine) equipment));
                }
                throw new InvalidUpdateException("Invalid update for equipment Id:" + id);
            default:
                throw new InvalidUpdateException("Unknown equipment type for equipment Id:" + id);
        }
    }

    public void deleteEquipment(long id) {
        if (!equipmentRepository.existsById(id)) {
            throw new NotFoundException("Invalid equipment Id:" + id);
        }
        equipmentRepository.deleteById(id);
    }


    private static Dumbells updateDumbells(Dumbells existingDumbells, Dumbells dumbells) {
        existingDumbells.setWeight(dumbells.getWeight());
        existingDumbells.setOccupied(dumbells.isOccupied());
        existingDumbells.setType(dumbells.getType());
        return existingDumbells;
    }

    public static Barbell updateBarbell(Barbell existingBarbell, Barbell barbell) {
        existingBarbell.setWeight(barbell.getWeight());
        existingBarbell.setOccupied(barbell.isOccupied());
        existingBarbell.setType(barbell.getType());
        return existingBarbell;
    }

    public static Machine updateMachine(Machine existingMachine, Machine machine) {
        existingMachine.setOccupied(machine.isOccupied());
        existingMachine.setCategory(machine.getCategory());
        return existingMachine;
    }

}
