package com.hella.ICTManager.service.impl;

import com.hella.ICTManager.entity.Fixture;
import com.hella.ICTManager.entity.Machine;
import com.hella.ICTManager.repository.FixtureRepository;
import com.hella.ICTManager.repository.MachineRepository;
import com.hella.ICTManager.service.FixtureService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component
public class FixtureServiceImpl implements FixtureService {
    private final FixtureRepository fixtureRepository;
    private final MachineRepository machineRepository;

    public FixtureServiceImpl(FixtureRepository fixtureRepository, MachineRepository machineRepository) {
        this.fixtureRepository = fixtureRepository;
        this.machineRepository = machineRepository;
    }

    @Override
    public void save(Fixture fixture) {
        fixtureRepository.save(fixture);
    }

    @Override
    public Fixture findById(long id) {
        return fixtureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fixture with id " + id + " not found"));
    }

    @Override
    public List<Fixture> findAll() {
        return fixtureRepository.findAll();
    }

    @Override
    public void update(long id, Fixture fixture) {
        Fixture oldFixture = fixtureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fixture with id " + id + " not found"));
        oldFixture.setFileName(fixture.getFileName());
        oldFixture.setBusiness(fixture.getBusiness());
        oldFixture.setProductName(fixture.getProductName());
        oldFixture.setProgramName(fixture.getProgramName());
        oldFixture.setMachines(fixture.getMachines());
        fixtureRepository.save(fixture);
    }

    @Override
    public void deleteById(long id) {
        fixtureRepository.deleteById(id);
    }

    @Override
    public void addFixtureToMachine(long fixtureId, long machineId) {
        Fixture fixture = fixtureRepository.findById(fixtureId)
                .orElseThrow(() -> new IllegalArgumentException("Fixture with id " + fixtureId + " not found"));
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new IllegalArgumentException("Machine with id " + machineId + " not found"));
        fixture.getMachines().add(machine);

        fixtureRepository.save(fixture);
    }

    public void createMaintenanceFixtureReport() {
        List<Fixture> fixtures = fixtureRepository.findAll(); //iau toate fixture-urile

        for (Fixture fixture : fixtures) {
            System.out.println("Fixture " + fixture.getFileName() + " has been reported for maintenance by ");
            try {

                doBusinessLogic(fixture);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void doBusinessLogic(Fixture fixture) throws FileNotFoundException {
        String filePath = "X:\\Maintenance\\";
        File file = new File(filePath+fixture.getFileName());

        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist");
        }

        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] words = line.split("\\s+");
            int counter = Integer.parseInt(words[0]);
            if (counter == 50_000) {
                String newline = "0 0 n";

                try(FileWriter fileWriter = new FileWriter(filePath+fixture.getFileName());
                    FileWriter fileWriter1 = new FileWriter(filePath + "contoare resetate.txt",true);) {
                    fileWriter.write(newline);

                    fileWriter1.write("Contorul fixture-ului " + fixture.getFileName() + " a fost resetat la 0 in data de: " + java.time.LocalDate.now() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
            {
                fixture.setCounter(Integer.parseInt(words[0]));
                fixtureRepository.save(fixture);
            }
        }
    }

    @Scheduled(cron = "0 45 13 * * ?")
    public void scheduleBusinessLogic() {
        List <Fixture> fixtures = fixtureRepository.findAll();
        for ( Fixture fixture : fixtures) {
            try {
                doBusinessLogic(fixture);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
