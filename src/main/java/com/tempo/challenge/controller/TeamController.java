package com.tempo.challenge.controller;

import com.tempo.challenge.service.TeamService;
import com.tempo.challenge.model.Team;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/teams")
@AllArgsConstructor
public class TeamController {

    private final TeamService business;

    @GetMapping
    ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(business.getAllTeams());
    }

    @PostMapping("/save")
    ResponseEntity<Team> saveTeam(@RequestBody Team team) {
        return ResponseEntity.ok().body(business.saveTeam(team));
    }

    @GetMapping("/{id}")
    ResponseEntity<Team> getTeamById(@PathVariable("id") String uuid) {
        Optional<Team> teamById = business.getTeamById(uuid);
        return teamById.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
