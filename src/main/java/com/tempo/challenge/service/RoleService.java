package com.tempo.challenge.service;

import com.tempo.challenge.model.Role;
import com.tempo.challenge.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public List<Role> getAllTeamRoles() {
        return repository.findAll();
    }

    public List<Role> save(List<Role> role) {
        return repository.saveAll(role);
    }
}
