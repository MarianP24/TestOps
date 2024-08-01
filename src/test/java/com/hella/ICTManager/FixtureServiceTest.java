package com.hella.ICTManager;

import com.hella.ICTManager.entity.Fixture;
import com.hella.ICTManager.repository.FixtureRepository;
import com.hella.ICTManager.service.impl.FixtureServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FixtureServiceTest {

    @Mock
    private FixtureRepository fixtureRepository;

    @InjectMocks
    private FixtureServiceImpl fixtureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
void createMaintenanceFixtureReport_whenCounterIs50_000_resetsCounter() throws FileNotFoundException {
        // Given
        Fixture fixture = new Fixture();
        fixture.setFileName("test_file");
        when(fixtureRepository.findAll()).thenReturn(List.of(fixture));

        // When
        fixtureService.createMaintenanceFixtureReport();

        // Then
        verify(fixtureRepository, times(1)).save(fixture);
        assertEquals(0, fixture.getCounter());
    }

    @Test
    void createMaintenanceFixtureReport_whenCounterIsNot50_000_doesNotResetCounter() throws FileNotFoundException {
        // Given
        Fixture fixture = new Fixture();
        fixture.setFileName("test_file");
        fixture.setCounter(10);
        when(fixtureRepository.findAll()).thenReturn(List.of(fixture));

        // When
        fixtureService.createMaintenanceFixtureReport();

        // Then
        verify(fixtureRepository, never()).save(fixture);
        assertEquals(10, fixture.getCounter());
    }
}