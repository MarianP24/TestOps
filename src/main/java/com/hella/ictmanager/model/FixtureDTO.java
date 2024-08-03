package com.hella.ictmanager.model;

import com.hella.ictmanager.entity.Fixture;
import com.hella.ictmanager.entity.Machine;

import java.util.Set;

public record FixtureDTO(String fileName, String programName, String productName, String business,
                         Set<Machine> machines) {

    public static FixtureDTO convertToDTO(Fixture fixture) {
        return new FixtureDTO(fixture.getFileName(), fixture.getProgramName(), fixture.getProductName(), fixture.getBusiness(), fixture.getMachines());
    }

    public Fixture convertToEntity() {
        Fixture fixture = new Fixture();
        fixture.setFileName(this.fileName());
        fixture.setBusiness(this.business());
        fixture.setProductName(this.productName());
        fixture.setProgramName(this.programName());
        return fixture;
    }
}
