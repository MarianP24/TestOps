package com.hella.ictmanager.controller;


import com.hella.ictmanager.model.FixtureDTO;
import com.hella.ictmanager.service.FixtureService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fixtures")
public class FixtureController {

    private final FixtureService fixtureService;

    public FixtureController(FixtureService fixtureService) {
        this.fixtureService = fixtureService;
    }

    @Operation(description = """
            {
              "fileName": "1430F1.wtg",
              "programName": "11430A.mtl",
              "productName": "CMU Plug-in",
              "business": "CMU old",
              "fixtureCounterSet":
            }
            """)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void save(@RequestBody FixtureDTO fixture) {
        fixtureService.save(fixture);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN', 'OPERATOR')")
    public FixtureDTO findById(@PathVariable long id) {
        return fixtureService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN', 'OPERATOR')")
    public List<FixtureDTO> getFixtures() {
        return fixtureService.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@PathVariable long id, @RequestBody FixtureDTO fixture) {
        fixtureService.update(id, fixture);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable long id) {
        fixtureService.deleteById(id);
    }

    @PostMapping("/{fixtureId}/machines/{machineId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void addFixtureToMachine(@PathVariable long fixtureId, @PathVariable long machineId) {
        fixtureService.addFixtureToMachine(fixtureId, machineId);
    }

    @PostMapping("/maintenance")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    public void createMaintenanceFixtureReport() {
        fixtureService.createMaintenanceFixtureReport();
    }
}
