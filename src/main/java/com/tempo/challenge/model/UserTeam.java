package com.tempo.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Intermediate class to set an user to a team and a role
 */
@Entity
@Table(name = "user_team", schema = "challenge_db")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTeam {
    /**
     * Composed primary key used to set unique user and team to a role
     */
    @EmbeddedId
    private UserTeamId userTeamId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", columnDefinition = "integer default 1")
    private Role role;
}
