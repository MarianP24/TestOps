package com.hella.ictmanager.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EndpointController implements ErrorController {

    @GetMapping("/endpoints")
    public String endpoints(Model model, Authentication authentication) {
        List<String> endpoints = new ArrayList<>();

        // Iterează prin autoritățile (rolurile) utilizatorului
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();

            // Adaugă endpointurile accesibile pe baza rolurilor
            if ("ROLE_ADMIN".equals(role)) {
                endpoints.add("/admin-endpoint");
            }
            if ("ROLE_TECHNICIAN".equals(role)) {
                endpoints.add("/technician-endpoint");
            }
            if ("ROLE_OPERATOR".equals(role)) {
                endpoints.add("/operator-endpoint");
            }
        }

        // Adaugă lista de endpointuri în model pentru a fi accesibilă în template
        model.addAttribute("endpoints", endpoints);
        return "endpointsView"; // Numele template-ului Thymeleaf
    }

    @RequestMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("error", "An error occurred. Please try again later.");
        return "error";
    }

    @GetMapping("/access-denied")
    public String accessDenied(Model model) {
        model.addAttribute("error", "You do not have permission to access this page.");
        return "accessDenied";
    }
}
