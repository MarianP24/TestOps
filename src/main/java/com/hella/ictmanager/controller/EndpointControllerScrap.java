//package com.hella.ictmanager.controller;
//
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@Controller
//public class EndpointControllerScrap implements ErrorController {
//
//    @Getter
//    static class Endpoint {
//        private final String url;
//        private final String methodName;
//        private final String className;
//
//        public Endpoint(String url, String methodName, String className) {
//            this.url = url;
//            this.methodName = methodName;
//            this.className = className;
//        }
//
//    }
//
//    @GetMapping("/endpoints")
//    public String endpoints(Model model, Authentication authentication) {
//        List<Endpoint> endpoints = new ArrayList<>();
//
//        for (GrantedAuthority authority : authentication.getAuthorities()) {
//            String role = authority.getAuthority();
//
//            log.info("User from endpoint controller has role: {}", role);
//
//            if ("ROLE_ADMIN".equals(role)) {
//                log.info("lalala {}",endpoints);
//                log.info("class name: {}", "com.hella.ictmanager.controller.FixtureController");
//                log.info("lalala2 {}",role);
//                discoverEndpointsForRole(endpoints, "com.hella.ictmanager.controller.FixtureController", role);
//                log.info("endpoints added to the listtttt: {}", endpoints);
//                discoverEndpointsForRole(endpoints, "com.hella.ictmanager.controller.MachineController", role);
//                discoverEndpointsForRole(endpoints, "com.hella.ictmanager.controller.UserController", role);
//                discoverEndpointsForRole(endpoints, "com.hella.ictmanager.controller.UserController", role);
//            }
//            if ("ROLE_TECHNICIAN".equals(role)) {
//                discoverEndpointsForRole(endpoints, "com.hella.ictmanager.controller.FixtureController", role);
//                discoverEndpointsForRole(endpoints, "com.hella.ictmanager.controller.MachineController", role);
//                discoverEndpointsForRole(endpoints, "com.hella.ictmanager.controller.UserController", role);
//            }
//            if ("ROLE_OPERATOR".equals(role)) {
//                discoverEndpointsForRole(endpoints, "com.hella.ictmanager.controller.FixtureController", role);
//                discoverEndpointsForRole(endpoints, "com.hella.ictmanager.controller.MachineController", role);
//                discoverEndpointsForRole(endpoints, "com.hella.ictmanager.controller.UserController", role);
//            }
//        }
//
//        // Adaugă lista de endpointuri în model pentru a fi accesibilă în template
//        model.addAttribute("endpoints", endpoints);
//        log.info("endpoints added to the list: {}", endpoints);
//        return "endpointsView"; // Numele template-ului Thymeleaf
//    }
//
//    private void discoverEndpointsForRole(List<Endpoint> endpoints, String className, String role) {
//        try {
//            Class<?> clazz = Class.forName(className);
//            Method[] methods = clazz.getDeclaredMethods();
//
//            for (Method method : methods) {
//                PreAuthorize preAuthorize = method.getAnnotation(PreAuthorize.class);
//                log.info("Checking method: {} with PreAuthorize: {}", method.getName(), preAuthorize);
//
//                if (preAuthorize != null) {
//                    log.info("PreAuthorize value: {}", preAuthorize.value());
//                    String[] roles = preAuthorize.value().replace("hasRole('", "").replace("')", "").split("', '");
//                    for (String r : roles) {
//                        if (r.equals(role.replace("ROLE_", ""))) {
//                            log.info("Method {} in class {} is accessible by role {}", method.getName(), className, role);
//                            RequestMapping mapping = method.getAnnotation(RequestMapping.class);
//                            GetMapping getMapping = method.getAnnotation(GetMapping.class);
//                            PostMapping postMapping = method.getAnnotation(PostMapping.class);
//                            PutMapping putMapping = method.getAnnotation(PutMapping.class);
//                            DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
//
//                            String url = extractUrl(mapping, getMapping, postMapping, putMapping, deleteMapping);
//
//                            if (!url.isEmpty()) {
//                                Endpoint endpoint = new Endpoint(url, method.getName(), clazz.getSimpleName());
//                                endpoints.add(endpoint);
//                            }
//                            break; // Stop checking other roles for this method
//                        }
//                    }
//                } else {
//                    log.info("No PreAuthorize annotation found on method {}", method.getName());
//                }
//            }
//        } catch (ClassNotFoundException e) {
//            log.error("Class not found: {}", className, e);
//        }
//    }
//
//
//    private String extractUrl(RequestMapping mapping, GetMapping getMapping, PostMapping postMapping, PutMapping putMapping, DeleteMapping deleteMapping) {
//        if (mapping != null && mapping.value().length > 0) {
//            return mapping.value()[0];
//        } else if (getMapping != null && getMapping.value().length > 0) {
//            return getMapping.value()[0];
//        } else if (postMapping != null && postMapping.value().length > 0) {
//            return postMapping.value()[0];
//        } else if (putMapping != null && putMapping.value().length > 0) {
//            return putMapping.value()[0];
//        } else if (deleteMapping != null && deleteMapping.value().length > 0) {
//            return deleteMapping.value()[0];
//        }
//        return "";
//    }
//
//
//    @RequestMapping("/error")
//    public String handleError(Model model) {
//        model.addAttribute("error", "An error occurred. Cannot display any endpoint.");
//        return "error";
//    }
//
//    @GetMapping("/access-denied")
//    public String accessDenied(Model model) {
//        model.addAttribute("error", "You do not have permission to access this page.");
//        return "accessDenied";
//    }
//}
