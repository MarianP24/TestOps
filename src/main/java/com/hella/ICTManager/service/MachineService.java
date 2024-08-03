package com.hella.ICTManager.service;

import com.hella.ICTManager.entity.Machine;
import com.hella.ICTManager.model.MachineDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MachineService {
    void save(MachineDTO machineDTO);

    MachineDTO findById(long id);

    List<MachineDTO> findAll();

    void update(long id, MachineDTO machineDTO);

    void deleteById(long id);
}
