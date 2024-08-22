package com.hella.ictmanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class EndpointController implements ErrorController {

    @GetMapping("/endpoints")
    public String endpoints(Model model, Authentication authentication) {
        List<String> endpoints = new ArrayList<>();

        // Iterează prin autoritățile (rolurile) utilizatorului
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();

            log.info("User from endpoint controller has role: {}", role);

            if ("ROLE_ADMIN".equals(role)) {
                endpoints.add("/fixtures");
                endpoints.add("/machines");
                endpoints.add("/users");
            }
            if ("ROLE_TECHNICIAN".equals(role)) {
                endpoints.add("/fixtures");
                endpoints.add("/machines");
                endpoints.add("/users");
            }
            if ("ROLE_OPERATOR".equals(role)) {
                endpoints.add("/fixtures");
                endpoints.add("/machines");
                endpoints.add("/users");
            }
        }

        // Adaugă lista de endpointuri în model pentru a fi accesibilă în template
        model.addAttribute("endpoints", endpoints);
        log.info("endpoints added to the list: {}", endpoints);
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
