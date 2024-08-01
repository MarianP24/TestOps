package com.hella.ICTManager.controller;


import com.hella.ICTManager.entity.Fixture;
import com.hella.ICTManager.service.FixtureService;
import com.hella.ICTManager.service.impl.FixtureServiceImpl;
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

    @Operation(description = "{\n" +
            "  \"fileName\": \"1430F1.wtg\",\n" +
            "  \"programName\": \"11430A.mtl\",\n" +
            "  \"productName\": \"CMU Plug-in\",\n" +
            "  \"business\": \"CMU old\",\n" +
            "  \"machines\": []\n" +
            "}")
    @PostMapping
    public void save(@RequestBody Fixture fixture) {
        fixtureService.save(fixture);
    }

    @GetMapping("/{id}")
    public Fixture findById(@RequestParam long id) {
        return fixtureService.findById(id);
    }

    @GetMapping
    public List<Fixture> getFixtures() {
        return fixtureService.findAll();
    }

    @PutMapping("/{id}")
    public void update(@RequestParam long id, @RequestBody Fixture fixture) {
        fixtureService.update(id, fixture);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@RequestParam long id) {
        fixtureService.deleteById(id);
    }

    @PostMapping("/{fixtureId}/machines/{machineId}")
    public void addFixtureToMachine(long fixtureId, long machineId) {
        fixtureService.addFixtureToMachine(fixtureId, machineId);
    }

    //Put este de update
    //Post este de create
    @PostMapping ("/maintenance")
    public void createMaintenanceFixtureReport() {
        fixtureService.createMaintenanceFixtureReport();
    }
}
