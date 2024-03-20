package com.project.ase_project.model.dto.match;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

/**
 * Represents an MetadataDto object from the Riot API (<a href="https://developer.riotgames.com/apis#match-v5/GET_getMatch/">...</a>)
 * This class is used to store meta information regarding the game.
 * This class is used by the Match class as an attribute.
 */

@Data
@Getter
@Setter
@ToString
public class MetadataDto {
    private String dataVersion;
    private String matchId;
    private ArrayList<String> participants;
}
