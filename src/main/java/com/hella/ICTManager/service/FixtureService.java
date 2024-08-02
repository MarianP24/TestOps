package com.hella.ICTManager.service;

import com.hella.ICTManager.entity.Fixture;
import com.hella.ICTManager.model.FixtureDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FixtureService {
    void save(FixtureDTO fixtureDTO);

    FixtureDTO findById(long id);

    List<FixtureDTO> findAll();

    void update(long id, FixtureDTO fixtureDTO);

    void deleteById(long id);

    void addFixtureToMachine(long fixtureId, long machineId);

    void createMaintenanceFixtureReport();
}
