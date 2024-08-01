package com.hella.ICTManager.service;

import com.hella.ICTManager.entity.Fixture;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FixtureService {
    void save(Fixture fixture);

    Fixture findById(long id);

    List<Fixture> findAll();

    void update(long id, Fixture fixture);

    void deleteById(long id);

    void addFixtureToMachine(long fixtureId, long machineId);

    void createMaintenanceFixtureReport();
}
