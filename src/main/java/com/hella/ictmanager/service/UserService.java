package com.hella.ictmanager.service;

import com.hella.ictmanager.model.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void save(UserDTO userDTO);

    UserDTO findById(long id);

    List<UserDTO> findAll();

    void update(long id, UserDTO userDTO);

    void deleteById(long id);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
