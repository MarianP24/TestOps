package com.hella.ictmanager.service;

import com.hella.ictmanager.entity.Machine;
import com.hella.ictmanager.model.MachineDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MachineService {
    void save(MachineDTO machineDTO);

    MachineDTO findById(long id);

    List<Machine> findAll();

    void update(long id, MachineDTO machineDTO);

    void deleteById(long id);
}
