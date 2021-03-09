package com.tempo.challenge.controller;

import com.tempo.challenge.exception.RoleNotFoundException;
import com.tempo.challenge.exception.UserNotFoundExpcetion;
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
    ResponseEntity<User> save(@RequestBody User user) throws RoleNotFoundException {
        return ResponseEntity.ok(business.saveUser(user));
    }

    @PostMapping("/saveAll")
    ResponseEntity<List<User>> saveAll(@RequestBody List<User> users) throws RoleNotFoundException {
        return ResponseEntity.ok(business.saveAll(users));
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable("id") String uuid) {
        Optional<User> userById = business.getUserById(uuid);
        return userById.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/save/{userId}/role/{roleId}")
    ResponseEntity<User> saveRoleToUser(@PathVariable("userId") String userId,
                                        @PathVariable("roleId") Long roleId) throws RoleNotFoundException, UserNotFoundExpcetion {
        return ResponseEntity.ok(business.saveRoleToUser(userId, roleId));
    }
}
