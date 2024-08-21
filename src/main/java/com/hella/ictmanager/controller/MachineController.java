package com.hella.ictmanager.controller;

import com.hella.ictmanager.model.MachineDTO;
import com.hella.ictmanager.service.impl.MachineServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public void save(@RequestBody MachineDTO machine) {
        machineService.save(machine);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN', 'OPERATOR')")
    public MachineDTO findById(@PathVariable long id) {
        return machineService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN', 'OPERATOR')")
    public List<MachineDTO> getMachines() {
        return machineService.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@PathVariable long id, @RequestBody MachineDTO machine) {
        machineService.update(id, machine);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable long id) {
        machineService.deleteById(id);
    }
}
