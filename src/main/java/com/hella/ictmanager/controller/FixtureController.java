package com.hella.ictmanager.controller;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hella.ictmanager.entity.Fixture;
import com.hella.ictmanager.model.FixtureDTO;
import com.hella.ictmanager.service.FixtureService;
import io.swagger.v3.oas.annotations.Operation;
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
    public void save(@RequestBody FixtureDTO fixture) {
        fixtureService.save(fixture);
    }

    @GetMapping("/{id}")
    public FixtureDTO findById(@PathVariable long id) {
        return fixtureService.findById(id);
    }

    @GetMapping
    public List<FixtureDTO> getFixtures() {
        return fixtureService.findAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody FixtureDTO fixture) {
        fixtureService.update(id, fixture);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        fixtureService.deleteById(id);
    }

    @PostMapping("/{fixtureId}/machines/{machineId}")
    public void addFixtureToMachine(@PathVariable long fixtureId, @PathVariable long machineId) {
        fixtureService.addFixtureToMachine(fixtureId, machineId);
    }

    @PostMapping("/maintenance")
    public void createMaintenanceFixtureReport() {
        fixtureService.createMaintenanceFixtureReport();
    }
}
