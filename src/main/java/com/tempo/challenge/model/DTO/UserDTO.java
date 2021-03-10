package com.tempo.challenge.model.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {
    private String name;
    private List<String> teamId;
}
