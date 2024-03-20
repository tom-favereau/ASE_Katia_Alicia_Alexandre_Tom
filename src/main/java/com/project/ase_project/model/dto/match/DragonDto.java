package com.project.ase_project.model.dto.match;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents an ObjectiveDto object from the Riot API (<a href="https://developer.riotgames.com/apis#match-v5/GET_getMatch/">...</a>)
 * This class is used to store information regarding the Dragon objective, including whether this team killed it
 * for the fist time and the number of time this teams has killed this objective.
 * This class is used by the Objectives class as an attribute.
 */

@Data
@Getter
@Setter
@ToString
public class DragonDto {
    private boolean first;
    private int kills;
}
