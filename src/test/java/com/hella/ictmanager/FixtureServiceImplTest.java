package com.hella.ictmanager;


import com.hella.ictmanager.entity.Fixture;
import com.hella.ictmanager.entity.Machine;
import com.hella.ictmanager.repository.FixtureRepository;
import com.hella.ictmanager.repository.MachineRepository;
import com.hella.ictmanager.service.impl.FixtureServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class FixtureServiceImplTest {

    @Mock
    private FixtureRepository fixtureRepository;

    @Mock
    private MachineRepository machineRepository;

    @InjectMocks
    private FixtureServiceImpl fixtureService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void addFixtureToMachine_whenMachineNotAssociated_addsMachine() {
        Fixture fixture = new Fixture();
        Machine machine = new Machine();
        when(fixtureRepository.findById(1L)).thenReturn(Optional.of(fixture));
        when(machineRepository.findById(1L)).thenReturn(Optional.of(machine));

        fixtureService.addFixtureToMachine(1L, 1L);

        verify(fixtureRepository, times(1)).save(fixture);
        assertTrue(fixture.getMachines().contains(machine));
    }

    @Test
    void addFixtureToMachine_whenMachineAlreadyAssociated_doesNotAddMachineAgain() {
        Fixture fixture = new Fixture();
        Machine machine = new Machine();
        fixture.getMachines().add(machine);
        when(fixtureRepository.findById(1L)).thenReturn(Optional.of(fixture));
        when(machineRepository.findById(1L)).thenReturn(Optional.of(machine));

        fixtureService.addFixtureToMachine(1L, 1L);

        verify(fixtureRepository, never()).save(any(Fixture.class));
    }

    @Test
    void findById_whenFixtureExists_returnsFixture() {
        Fixture fixture = new Fixture();
        when(fixtureRepository.findById(1L)).thenReturn(Optional.of(fixture));

        Fixture result = fixtureService.findById(1L).convertToEntity();

        assertNotNull(result);
    }

    @Test
    void findById_whenFixtureDoesNotExist_throwsException() {
        when(fixtureRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> fixtureService.findById(1L));
    }
}
