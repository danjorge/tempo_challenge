package com.tempo.challenge.controller;

import com.tempo.challenge.exception.RoleNotFoundException;
import com.tempo.challenge.exception.UserNotFoundExpcetion;
import com.tempo.challenge.model.DTO.UserDTO;
import com.tempo.challenge.service.UserService;
import com.tempo.challenge.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "users")
@AllArgsConstructor
public class UserController {

    private final UserService business;

    @GetMapping
    ResponseEntity<List<User>> getAllTeams() {
        return ResponseEntity.ok(business.getAllUsers());
    }

    @PostMapping("/save")
    ResponseEntity<User> save(@RequestBody UserDTO userDTO) throws RoleNotFoundException {
        return ResponseEntity.ok(business.saveUser(userDTO));
    }

    @PostMapping("/saveAll")
    ResponseEntity<List<UserDTO>> saveAll(@RequestBody List<UserDTO> userDTOList) throws RoleNotFoundException {
        return ResponseEntity.ok(business.saveAll(userDTOList));
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable("id") String uuid) {
        Optional<User> userById = business.getUserById(uuid);
        return userById.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/save/{userId}/team/{teamId}/role/{roleId}")
    ResponseEntity<User> saveRoleToUser(@PathVariable("userId") String userId,
                                        @PathVariable("teamId") String teamId,
                                        @PathVariable("roleId") Long roleId) throws RoleNotFoundException, UserNotFoundExpcetion {
        return ResponseEntity.ok(business.saveRoleToUser(userId, teamId, roleId));
    }
}
