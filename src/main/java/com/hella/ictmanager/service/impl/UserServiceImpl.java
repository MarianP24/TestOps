package com.hella.ictmanager.service.impl;

import com.hella.ictmanager.entity.User;
import com.hella.ictmanager.model.UserDTO;
import com.hella.ictmanager.repository.UserRepository;
import com.hella.ictmanager.security.Role;
import com.hella.ictmanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(UserDTO userDTO) {
        userRepository.save(userDTO.convertToEntity());
        log.info("User {} has been saved", userDTO.username());
}
    @Override
    public UserDTO findById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
        log.info("User {} has been found", user.getUsername());
        return UserDTO.convertToDTO(user);
}
    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        log.info("Found {} users", users.size());
        return users.stream()
                .map(UserDTO::convertToDTO)
                .toList();
}
    @Override
    public void update(long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        user.setRole(userDTO.role());
        userRepository.save(user);
        log.info("User {} has been updated", user.getUsername());
}
    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
        log.info("User with id {} has been deleted", id);
}

    @Override
    @Cacheable("userDetails")
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));

        // Adaugă prefixul ROLE_ în autorități
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
