package com.hella.ictmanager.controller;

import com.hella.ictmanager.model.FixtureDTO;
import com.hella.ictmanager.service.FixtureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fixturesThymeleaf")
public class FixtureThymeleafController {

    private final FixtureService fixtureService;

    public FixtureThymeleafController(FixtureService fixtureService) {
        this.fixtureService = fixtureService;
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
        return "fixtureControllerForms/listEndpointsController"; // Afișează pagina cu lista de stringuri
    }

    @GetMapping("/save fixture")
    public String saveFixtureForm(Model model) {
        model.addAttribute("fixtureDTO", new FixtureDTO("", "", "", "", 0)); // Inițializează un obiect gol de FixtureDTO
        return "fixtureControllerForms/saveFixtures"; // Afișează formularul de creare a unui fixture
    }

    @PostMapping("/save fixture")
    public String saveFixture(@ModelAttribute FixtureDTO fixtureDTO, Model model) {
        fixtureService.save(fixtureDTO);
        model.addAttribute("message", "Fixture saved successfully");
        return "fixtureControllerForms/saveFixtures";
    }


    @GetMapping("/get fixture by ID")
    public String findFixtureByIdForm() {
        // Logica pentru crearea fixture-ului
        return "fixtureControllerForms/getFixtureById"; // Afișează formularul de căutare a unui fixture
    }

    @GetMapping("/list fixtures")
    public String listFixturesForm(Model model) {
        model.addAttribute("action", GET_FIXTURES);
        return "fixtureControllerForms/listFixtures"; // Afișează formularul cu fixture-urile existente
    }

    @PutMapping("/update fixture by ID")
    public String updateFixtureForm() {
        // Logica pentru actualizarea fixture-ului
        return "fixtureControllerForms/updateFixture"; // Afișează formularul de actualizare a unui fixture
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
