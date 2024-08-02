package com.hella.ICTManager.service;

import com.hella.ICTManager.entity.Machine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MachineService {
    void save(Machine machine);

    Machine findById(long id);

    List<Machine> findAll();

    void update(long id, Machine machine);

    void deleteById(long id);
}
