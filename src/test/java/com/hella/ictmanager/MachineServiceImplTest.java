package com.hella.ictmanager;

import com.hella.ictmanager.entity.Machine;
import com.hella.ictmanager.model.MachineDTO;
import com.hella.ictmanager.repository.MachineRepository;
import com.hella.ictmanager.service.impl.MachineServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MachineServiceImplTest {

    @Mock
    private MachineRepository machineRepository;

    @InjectMocks
    private MachineServiceImpl machineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_whenMachineExists_returnsMachineDTO() {
        Machine machine = new Machine();
        machine.setId(1L);
        machine.setEquipmentName("Test Machine");
        when(machineRepository.findById(1L)).thenReturn(Optional.of(machine));

        MachineDTO result = machineService.findById(1L);

        assertNotNull(result);
        assertEquals("Test Machine", result.equipmentName());
    }

    @Test
    void findById_whenMachineDoesNotExist_throwsException() {
        when(machineRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> machineService.findById(1L));
    }

    @Test
    void findAll_whenMachinesExist_returnsListOfMachineDTOs() {
        Machine machine1 = new Machine();
        machine1.setEquipmentName("Machine 1");
        Machine machine2 = new Machine();
        machine2.setEquipmentName("Machine 2");
        when(machineRepository.findAll()).thenReturn(List.of(machine1, machine2));

        List<Machine> result = machineService.findAll();

        assertEquals(2, result.size());
    }

//    @Test
//    void update_whenMachineExists_updatesMachine() {
//        Machine oldMachine = new Machine();
//        oldMachine.setId(1L);
//        oldMachine.setEquipmentName("Old Machine");
//        MachineDTO machineDTO = new MachineDTO("Updated Machine", "Factory B", "67890", 4, Set.of());
//        when(machineRepository.findById(1L)).thenReturn(Optional.of(oldMachine));
//
//        machineService.update(1L, machineDTO);
//
//        verify(machineRepository, times(1)).save(oldMachine);
//        assertEquals("Updated Machine", oldMachine.getEquipmentName());
//    }

//    @Test
//    void update_whenMachineDoesNotExist_throwsException() {
//        MachineDTO machineDTO = new MachineDTO("Updated Machine", "Factory B", "67890", 4, Set.of());
//        when(machineRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(IllegalArgumentException.class, () -> machineService.update(1L, machineDTO));
//    }

    @Test
    void deleteById_whenMachineExists_deletesMachine() {
        doNothing().when(machineRepository).deleteById(1L);

        machineService.deleteById(1L);

        verify(machineRepository, times(1)).deleteById(1L);
    }
}