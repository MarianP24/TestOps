package com.hella.ictmanager.controller;

import com.hella.ictmanager.model.UserDTO;
import com.hella.ictmanager.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public void save(@RequestBody UserDTO user) {
        userService.save(user);
    }

    @GetMapping("/{id}")
    public UserDTO findByID(@PathVariable long id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.findAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody UserDTO user) {
        userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        userService.deleteById(id);
    }

}
