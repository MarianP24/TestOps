package com.hella.ictmanager.model;

import com.hella.ictmanager.entity.Machine;

public record MachineDTO(String equipmentName, String equipmentType, String serialNumber, int internalFactory) {

    public Machine convertToEntity() {
        Machine machine = new Machine();
        machine.setEquipmentName(this.equipmentName());
        machine.setEquipmentType(this.equipmentType());
        machine.setSerialNumber(this.serialNumber());
        machine.setInternalFactory(this.internalFactory());
        return machine;
    }

    public static MachineDTO convertToDTO(Machine machine) {
        return new MachineDTO(machine.getEquipmentName(), machine.getEquipmentType(), machine.getSerialNumber(), machine.getInternalFactory());
    }
}
