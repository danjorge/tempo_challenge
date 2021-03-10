package com.tempo.challenge.service;

import com.tempo.challenge.exception.RoleNotFoundException;
import com.tempo.challenge.exception.TeamNotFoundException;
import com.tempo.challenge.exception.UserNotFoundExpcetion;
import com.tempo.challenge.model.*;
import com.tempo.challenge.model.DTO.UserDTO;
import com.tempo.challenge.repository.RoleRepository;
import com.tempo.challenge.repository.TeamRepository;
import com.tempo.challenge.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class responsible to make all the logic to userController
 */
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final TeamRepository teamRepository;
    private static final Long ROLE_DEVELOPER = 1L;


    /**
     * Method to compose class USER by USERDTO and a List of Teams to save
     * @param userDTO
     * @return a user saved
     * @throws RoleNotFoundException
     */
    public User saveUser(UserDTO userDTO) throws RoleNotFoundException {
        User user = User.builder().name(userDTO.getName()).build();
        List<Team> teams = teamRepository.findAllById(userDTO.getTeamId());

        if (CollectionUtils.isEmpty(teams)) {
            throw new TeamNotFoundException("Teams not found.");
        }

        return save(user, ROLE_DEVELOPER, teams);
    }


    /**
     * Method to save user
     * @param user
     * @param roleId
     * @param teams
     * @return a class user saved
     * @throws RoleNotFoundException
     */
    @Transactional
    public User save(User user, Long roleId, List<Team> teams) throws RoleNotFoundException {
        Optional<Role> role = roleRepository.findById(roleId);

        if (role.isEmpty()) {
            throw new RoleNotFoundException("Role not found.");
        }

        user = repository.save(user);
        composeFieldsAndUpdate(user, teams, role.get());

        return user;
    }

    /**
     * Method to compose Fields before save and save
     * @param user
     * @param teams
     * @param role
     */
    private void composeFieldsAndUpdate(User user, List<Team> teams, Role role) {
        List<UserTeam> userTeams = new ArrayList<>();
        teams.forEach(team -> {
            UserTeamId userTeamId = UserTeamId.builder().userId(user.getId()).teamId(team.getId()).build();
            UserTeam userTeam = UserTeam.builder().userTeamId(userTeamId).role(role).build();
            userTeams.add(userTeam);
        });

        user.setUserTeams(userTeams);
        repository.save(user);
    }


    /**
     * Method to save a list of Users
     * @param users
     * @return a list of users
     * @throws RoleNotFoundException
     */
    public List<UserDTO> saveAll(List<UserDTO> users) throws RoleNotFoundException {
        for (UserDTO user : users) {
            try {
                saveUser(user);
            } catch (RoleNotFoundException e) {
                throw new RoleNotFoundException(e.getMessage());
            }
        }
        return users;
    }


    /**
     * Method to get user by id
     * @param uuid
     * @return user
     */
    public Optional<User> getUserById(String uuid) {
        return repository.findById(uuid);
    }

    /**
     * Method to get all users saved in database
     * @return list of users
     */
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    /**
     * Method responsible to put a role in a user
     * @param userId
     * @param teamId
     * @param roleId
     * @return user
     * @throws RoleNotFoundException
     * @throws UserNotFoundExpcetion
     * @throws TeamNotFoundException
     */
    public User saveRoleToUser(String userId, String teamId, Long roleId) throws RoleNotFoundException, UserNotFoundExpcetion, TeamNotFoundException {
        Optional<User> userById = getUserById(userId);
        Optional<Team> teamById = teamRepository.findById(teamId);

        if (userById.isEmpty()) {
            throw new UserNotFoundExpcetion("User not found");
        }

        if (teamById.isEmpty()) {
            throw new TeamNotFoundException("Team not found.");
        }

        return save(userById.get(), roleId, List.of(teamById.get()));
    }
}
