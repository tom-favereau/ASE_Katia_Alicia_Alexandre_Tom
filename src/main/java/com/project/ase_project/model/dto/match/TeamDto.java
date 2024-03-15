package com.project.ase_project.model.dto.match;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

/**
 * Represents a TeamDto object from the Riot API (<a href="https://developer.riotgames.com/apis#match-v5/GET_getMatch/">...</a>)
 * This class is used to store the information on a team in a match.
 * This class is used by the Info class as an element of an ArrayList.
 */

@Data
@Getter
@Setter
@ToString
public class TeamDto {
    private ArrayList<BanDto> bans;
    private ObjectivesDto objectives;
    private int teamId;
    private boolean win;
}
