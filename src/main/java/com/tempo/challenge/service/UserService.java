package com.tempo.challenge.service;

import com.tempo.challenge.exception.RoleNotFoundException;
import com.tempo.challenge.exception.UserNotFoundExpcetion;
import com.tempo.challenge.model.Role;
import com.tempo.challenge.model.User;
import com.tempo.challenge.repository.RoleRepository;
import com.tempo.challenge.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository repository;
    private static final Long ROLE_DEVELOPER = 1L;

    public User saveUser(User user) throws RoleNotFoundException {
        return save(user, ROLE_DEVELOPER);
    }

    public User save(User user, Long roleId) throws RoleNotFoundException {
        Optional<Role> role = roleRepository.findById(roleId);

        if (role.isEmpty()) {
            throw new RoleNotFoundException("Role not found.");
        }

        user.setRole(role.get());
        return repository.save(user);
    }

    public List<User> saveAll(List<User> users) throws RoleNotFoundException {
        for (User user : users) {
            try {
                saveUser(user);
            } catch (Exception e) {
                throw new RoleNotFoundException(e.getMessage());
            }
        }
        return users;
    }

    public Optional<User> getUserById(String uuid) {
        return repository.findById(uuid);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User saveRoleToUser(String userId, Long roleId) throws RoleNotFoundException, UserNotFoundExpcetion {
        Optional<User> userById = getUserById(userId);

        if (userById.isEmpty()) {
            throw new UserNotFoundExpcetion("User not found");
        }

        return save(userById.get(), roleId);
    }
}
