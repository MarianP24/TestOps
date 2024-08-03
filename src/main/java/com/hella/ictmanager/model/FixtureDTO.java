package com.hella.ictmanager.model;

import com.hella.ictmanager.entity.Fixture;

public record FixtureDTO(String fileName, String programName, String productName, String business) {

    public static FixtureDTO convertToDTO(Fixture fixture) {
        return new FixtureDTO(fixture.getFileName(), fixture.getProgramName(), fixture.getProductName(), fixture.getBusiness());
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
