package com.hella.ictmanager.controller;

import com.hella.ictmanager.model.MachineDTO;
import com.hella.ictmanager.service.impl.MachineServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/machines")
public class MachineController {
    private final MachineServiceImpl machineService;

    public MachineController(MachineServiceImpl machineService) {
        this.machineService = machineService;
    }

    @PostMapping
    public void save(@RequestBody MachineDTO machine) {
        machineService.save(machine);
    }

    @GetMapping("/{id}")
    public MachineDTO findById(@PathVariable long id) {
        return machineService.findById(id);
    }

    @GetMapping
    public List<MachineDTO> getMachines() {
        return machineService.findAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody MachineDTO machine) {
        machineService.update(id, machine);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        machineService.deleteById(id);
    }
}
