package com.hella.ictmanager.controller;

import com.hella.ictmanager.entity.Fixture;
import com.hella.ictmanager.model.FixtureDTO;
import com.hella.ictmanager.repository.FixtureRepository;
import com.hella.ictmanager.service.FixtureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/fixturesThymeleaf")
public class FixtureThymeleafController {

    private final FixtureService fixtureService;
    private final FixtureRepository fixtureRepository;

    public FixtureThymeleafController(FixtureService fixtureService, FixtureRepository fixtureRepository) {
        this.fixtureService = fixtureService;
        this.fixtureRepository = fixtureRepository;
    }

    private static final String SAVE_FIXTURE = "insert fixture in Database";


    @GetMapping("/listEndpointsController")
    public String showFixtureControllerPage(Model model) {
        model.addAttribute("saveFixture", SAVE_FIXTURE);
        return "fixtureControllerForms/listEndpointsController";
    }

    @GetMapping("/saveFixture")
    public String saveFixtureForm(Model model) {
        model.addAttribute("fixtureDTO", new FixtureDTO("", "", "", "", 0));
        return "fixtureControllerForms/saveFixtures";
    }

    @PostMapping("/saveFixture")
    public String saveFixture(@ModelAttribute FixtureDTO fixtureDTO, Model model) {
        fixtureService.save(fixtureDTO);
        model.addAttribute("message", "Fixture saved successfully");
        return "fixtureControllerForms/saveFixtures";
    }


    @GetMapping("/getFixtureByID")
    public String findFixtureByIdForm(Model model, @RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            try {
                FixtureDTO fixture = fixtureService.findById(id);
                model.addAttribute("fixture", fixture); // Adăugăm fixture-ul găsit în model
                model.addAttribute("message", "Fixture found");
            } catch (IllegalArgumentException e) {
                model.addAttribute("errorMessage", e.getMessage());
            }
        } else {
            model.addAttribute("fixture", null); // Fără date inițiale dacă nu există ID-ul
        }
        return "fixtureControllerForms/getFixtureById"; // Afișează formularul de căutare a unui fixture
    }

    @GetMapping("/listFixtures")
    public String listFixturesForm(Model model) {
        List<Fixture> fixtures = fixtureRepository.findAll();
        model.addAttribute("fixtures", fixtures);
        return "fixtureControllerForms/listFixtures"; // Afișează formularul cu fixture-urile existente
    }

    @GetMapping("/updateFixtureByID")
    public String updateFixtureForm(Model model) {
        model.addAttribute("fixture", new Fixture()); // Provide an empty Fixture object for the form
        return "fixtureControllerForms/updateFixture"; // Redirect to the update fixture form
    }

    @PostMapping("/updateFixtureByID")
    public String loadUpdateFixtureForm(@RequestParam("id") Long id, Model model) {
        try {
            Fixture fixture = fixtureService.findEntityById(id); // Get the fixture by ID
            model.addAttribute("fixture", fixture);
            return "fixtureControllerForms/updateFixture"; // Display the form populated with the fixture data
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "fixtureControllerForms/updateFixture"; // Show error message in the same form
        }
    }

    @PostMapping("/updateFixture")
    public String updateFixture(@ModelAttribute Fixture fixture, Model model) {
        try {
            fixtureService.update(fixture.getId(), FixtureDTO.convertToDTO(fixture));
            model.addAttribute("message", "Fixture updated successfully");
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "fixtureControllerForms/updateFixture";
    }

    @GetMapping("/deleteFixtureByID")
    public String deleteFixtureForm(Model model) {
        model.addAttribute("fixture", new Fixture()); // Provide an empty Fixture object for the form
        return "fixtureControllerForms/deleteFixtureByID"; // Redirect to the delete fixture form
    }

    @PostMapping("/deleteFixtureByID")
    public String deleteFixture(@RequestParam("id") Long id, Model model) {
        try {
            fixtureService.deleteById(id);
            model.addAttribute("message", "Fixture deleted successfully");
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "fixtureControllerForms/deleteFixtureByID";
    }

    @PostMapping("/addFixtureToMachine")
    public String addFixtureToMachineForm() {
        // Logica pentru adăugarea fixture-ului la o mașină
        return "fixtureControllerForms/addFixtureToMachine"; // Afișează formularul de adăugare a unui fixture la o mașină
    }

    @PostMapping("/createMaintenanceReport")
    public String createMaintenanceReportForm() {
        // Logica pentru crearea raportului de mentenanță
        return "fixtureControllerForms/createMaintenanceReport"; // Afișează formularul de creare a unui raport de mentenanță
    }
}
