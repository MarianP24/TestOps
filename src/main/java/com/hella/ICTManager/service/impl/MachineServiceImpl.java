package com.hella.ICTManager.service.impl;

import com.hella.ICTManager.entity.Machine;
import com.hella.ICTManager.model.MachineDTO;
import com.hella.ICTManager.repository.MachineRepository;
import com.hella.ICTManager.service.MachineService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MachineServiceImpl implements MachineService {
    private final MachineRepository machineRepository;

    public MachineServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public void save(MachineDTO machineDTO) {
        machineRepository.save(machineDTO.convertToEntity());
    }

    @Override
    public MachineDTO findById(long id) {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Machine with id " + id + " not found"));
        return MachineDTO.convertToDTO(machine);
    }

    @Override
    public List<MachineDTO> findAll() {
        List<Machine> machines = machineRepository.findAll();
        return machines.stream()
                .map(MachineDTO::convertToDTO)
                .toList();
    }

    @Override
    public void update(long id, MachineDTO machineDTO) {
        Machine oldMachine = machineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Machine with id " + id + " not found"));
        oldMachine.setEquipmentName(machineDTO.equipmentName());
        oldMachine.setInternalFactory(machineDTO.internalFactory());
        oldMachine.setSerialNumber(machineDTO.serialNumber());
        oldMachine.setEquipmentType(machineDTO.equipmentType());
        oldMachine.setFixtures(machineDTO.fixtures());
        machineRepository.save(oldMachine);
    }

    @Override
    public void deleteById(long id) {
        machineRepository.deleteById(id);
    }
}
