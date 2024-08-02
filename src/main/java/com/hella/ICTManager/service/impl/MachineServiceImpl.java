package com.hella.ICTManager.service.impl;

import com.hella.ICTManager.entity.Machine;
import com.hella.ICTManager.repository.MachineRepository;
import com.hella.ICTManager.service.MachineService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class MachineServiceImpl implements MachineService {
    private final MachineRepository machineRepository;

    public MachineServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public void save(Machine machine) {
        machineRepository.save(machine);
    }

    public Machine findById(long id) {
        return machineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Machine with id " + id + " not found"));
    }

    public List<Machine> findAll() {
        return machineRepository.findAll();
    }

    public void update(long id, Machine machine) {
        Machine oldMachine = machineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Machine with id " + id + " not found"));
        oldMachine.setEquipmentName(machine.getEquipmentName());
        oldMachine.setInternalFactory(machine.getInternalFactory());
        oldMachine.setSerialNumber(machine.getSerialNumber());
        oldMachine.setEquipmentType(machine.getEquipmentType());
        oldMachine.setFixtures(machine.getFixtures());
        machineRepository.save(machine);
    }

    public void deleteById(long id) {
        machineRepository.deleteById(id);
    }
}
