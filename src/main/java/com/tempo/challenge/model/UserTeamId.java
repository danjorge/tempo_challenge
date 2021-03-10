package com.tempo.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * Class with the composed primary key
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserTeamId implements Serializable {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;
    private String userId;
    private String teamId;
}
