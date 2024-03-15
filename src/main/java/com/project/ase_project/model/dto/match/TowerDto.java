package com.project.ase_project.model.dto.match;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents an ObjectiveDto object from the Riot API (<a href="https://developer.riotgames.com/apis#match-v5/GET_getMatch/">...</a>)
 * This class is used to store information regarding the tower buildings in the game, including whether this team
 * destroyed the first tower and the number of time this teams has destroyed towers.
 * This class is used by the Objectives class as an attribute.
 */

@Data
@Getter
@Setter
@ToString
public class TowerDto {
    private boolean first;
    private int kills;
}
