package com.tempo.challenge.service;

import com.tempo.challenge.model.DTO.UserDTO;
import com.tempo.challenge.model.*;
import com.tempo.challenge.repository.RoleRepository;
import com.tempo.challenge.repository.TeamRepository;
import com.tempo.challenge.repository.UserRepository;
import com.tempo.challenge.utils.BaseUnitTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

class UserServiceTest extends BaseUnitTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("Save User")
    void saveUser() {
        //Given
        UserDTO userDTO = UserDTO.builder().name("NameTest").teamId(List.of("42cb6400-82ae-4088-945c-bf4996e9264b")).build();
        Team team = Team.builder().id("42cb6400-82ae-4088-945c-bf4996e9264b").name("Illustrious Spaniel").build();
        Optional<Role> role = Optional.of(Role.builder().id(1L).description("Developer").build());
        UserTeamId userTeamId = UserTeamId.builder().userId("f19b6eae-8055-4109-b3c9-dec3b0d07a7a").teamId("42cb6400-82ae-4088-945c-bf4996e9264b").build();
        UserTeam userTeam = UserTeam.builder().userTeamId(userTeamId).role(role.get()).build();
        User expectedUser = User.builder().id(userTeamId.getUserId()).name("Candice").userTeams(List.of(userTeam)).build();

        given(teamRepository.findAllById(anyList())).willReturn(List.of(team));
        given(roleRepository.findById(anyLong())).willReturn(role);
        given(userRepository.save(any())).willReturn(expectedUser);
        //When
        User actualUser = userService.saveUser(userDTO);

        //Then
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    @DisplayName("Save all")
    void saveAll() {
        //
        UserDTO userDTO = UserDTO.builder().name("NameTest").teamId(List.of("42cb6400-82ae-4088-945c-bf4996e9264b")).build();
        List<UserDTO> userDTOS = List.of(userDTO);
        Team team = Team.builder().id("42cb6400-82ae-4088-945c-bf4996e9264b").name("Illustrious Spaniel").build();
        Optional<Role> role = Optional.of(Role.builder().id(1L).description("Developer").build());
        UserTeamId userTeamId = UserTeamId.builder().userId("f19b6eae-8055-4109-b3c9-dec3b0d07a7a").teamId("42cb6400-82ae-4088-945c-bf4996e9264b").build();
        UserTeam userTeam = UserTeam.builder().userTeamId(userTeamId).role(role.get()).build();
        User expectedUser = User.builder().id(userTeamId.getUserId()).name("Candice").userTeams(List.of(userTeam)).build();

        given(teamRepository.findAllById(anyList())).willReturn(List.of(team));
        given(roleRepository.findById(anyLong())).willReturn(role);
        given(userRepository.save(any())).willReturn(expectedUser);
        //When
        List<UserDTO> actualUsers = userService.saveAll(userDTOS);

        //Then
        assertThat(actualUsers).isEqualTo(userDTOS);
    }

    @Test
    @DisplayName("Save role to user")
    void saveRoleToUser() {
        //Given
        Team team = Team.builder().id("42cb6400-82ae-4088-945c-bf4996e9264b").name("Illustrious Spaniel").build();
        Optional<Role> role = Optional.of(Role.builder().id(1L).description("Developer").build());
        UserTeamId userTeamId = UserTeamId.builder().userId("f19b6eae-8055-4109-b3c9-dec3b0d07a7a").teamId("42cb6400-82ae-4088-945c-bf4996e9264b").build();
        UserTeam userTeam = UserTeam.builder().userTeamId(userTeamId).role(role.get()).build();
        User expectedUser = User.builder().id(userTeamId.getUserId()).name("Candice").userTeams(List.of(userTeam)).build();

        given(teamRepository.findAllById(anyList())).willReturn(List.of(team));
        given(teamRepository.findById(anyString())).willReturn(Optional.of(team));
        given(roleRepository.findById(anyLong())).willReturn(role);
        given(userRepository.findById(anyString())).willReturn(Optional.of(expectedUser));
        given(userRepository.save(any())).willReturn(expectedUser);
        //When
        User actualUser = userService.saveRoleToUser(expectedUser.getId(), team.getId(), role.get().getId());

        //Then
        assertThat(actualUser).isEqualTo(expectedUser);
    }
}