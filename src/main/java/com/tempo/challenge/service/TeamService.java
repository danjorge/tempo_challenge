package com.tempo.challenge.service;

import com.tempo.challenge.model.Team;
import com.tempo.challenge.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository repository;

    public Team saveTeam(Team team) {
        return repository.save(team);
    }

    public List<Team> getAllTeams() {
        return repository.findAll();
    }

    public Optional<Team> getTeamById(String uuid) {
        return repository.findById(uuid);
    }
}
