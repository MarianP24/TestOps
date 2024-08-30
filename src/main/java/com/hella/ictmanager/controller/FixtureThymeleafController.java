package com.hella.ictmanager.controller;

import com.hella.ictmanager.entity.Fixture;
import com.hella.ictmanager.model.FixtureDTO;
import com.hella.ictmanager.repository.FixtureRepository;
import com.hella.ictmanager.service.FixtureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private static final String GET_FIXTURE_BY_ID = "find fixture by ID";
    private static final String GET_FIXTURES = "List fixtures";
    private static final String UPDATE_FIXTURE = "update fixture by ID ";
    private static final String DELETE_FIXTURE = "delete fixture by ID";
    private static final String ADD_FIXTURE_TO_MACHINE = "Add fixture to machine";
    private static final String CREATE_MAINTENANCE_REPORT = "Create maintenance report";


    @GetMapping("/listEndpointsController")
    public String showFixtureControllerPage(Model model) {
        model.addAttribute("saveFixture", SAVE_FIXTURE);
        model.addAttribute("findFixtureById", GET_FIXTURE_BY_ID);
        model.addAttribute("listFixtures", GET_FIXTURES);
        model.addAttribute("updateFixturebyID", UPDATE_FIXTURE);
        model.addAttribute("deleteFixturebyID", DELETE_FIXTURE);
        model.addAttribute("addFixtureToMachine", ADD_FIXTURE_TO_MACHINE);
        model.addAttribute("createMaintenanceReport", CREATE_MAINTENANCE_REPORT);
        return "fixtureControllerForms/listEndpointsController";
    }

    @GetMapping("/save fixture")
    public String saveFixtureForm(Model model) {
        model.addAttribute("fixtureDTO", new FixtureDTO("", "", "", "", 0));
        return "fixtureControllerForms/saveFixtures";
    }

    @PostMapping("/save fixture")
    public String saveFixture(@ModelAttribute FixtureDTO fixtureDTO, Model model) {
        fixtureService.save(fixtureDTO);
        model.addAttribute("message", "Fixture saved successfully");
        return "fixtureControllerForms/saveFixtures";
    }


    @GetMapping("/get fixture by ID")
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

    @GetMapping("/list fixtures")
    public String listFixturesForm(Model model) {
        List<Fixture> fixtures = fixtureRepository.findAll();
        model.addAttribute("fixtures", fixtures);
        return "fixtureControllerForms/listFixtures"; // Afișează formularul cu fixture-urile existente
    }

    @GetMapping("/update fixture by ID")
    public String updateFixtureForm(Model model) {
        model.addAttribute("fixtureDTO", new FixtureDTO("", "", "", "", 0));
        return "fixtureControllerForms/updateFixture"; // Afișează formularul de actualizare a unui fixture după ID
    }

    @DeleteMapping("/delete fixture by ID")
    public String deleteFixtureForm() {
        // Logica pentru ștergerea fixture-ului
        return "fixtureControllerForms/deleteFixtureByID"; // Afișează formularul de ștergere a unui fixture dupa ID
    }

    @PostMapping("/add fixture to machine")
    public String addFixtureToMachineForm() {
        // Logica pentru adăugarea fixture-ului la o mașină
        return "fixtureControllerForms/addFixtureToMachine"; // Afișează formularul de adăugare a unui fixture la o mașină
    }

    @PostMapping("/create maintenance report")
    public String createMaintenanceReportForm() {
        // Logica pentru crearea raportului de mentenanță
        return "fixtureControllerForms/createMaintenanceReport"; // Afișează formularul de creare a unui raport de mentenanță
    }
}
