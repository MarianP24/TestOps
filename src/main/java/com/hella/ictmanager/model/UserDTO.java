package com.hella.ictmanager.model;

import com.hella.ictmanager.entity.User;
import com.hella.ictmanager.security.Role;

public record UserDTO(String username, String password, Role role) {

    public User convertToEntity() {
        User user = new User();
        user.setUsername(this.username());
        user.setPassword("{noop}" + this.password());
        user.setRole(this.role());
        return user;
    }

    public static UserDTO convertToDTO(User user) {
        return new UserDTO(user.getUsername(), user.getPassword(), user.getRole());
    }
}