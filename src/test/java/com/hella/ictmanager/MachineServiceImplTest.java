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
    void deleteById_whenMachineExists_deletesMachine() {
        doNothing().when(machineRepository).deleteById(1L);

        machineService.deleteById(1L);

        verify(machineRepository, times(1)).deleteById(1L);
    }
}