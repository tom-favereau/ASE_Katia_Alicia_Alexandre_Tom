package com.project.ase_project.model.dto.match;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents an PerkStatsDto object from the Riot API (<a href="https://developer.riotgames.com/apis#match-v5/GET_getMatch/">...</a>)
 * This class is used to store the information on the minor stat runes.
 * This class is used by the Perks class as an attribute.
 */

@Data
@Getter
@Setter
@ToString
public class StatPerksDto {
    private int defense;
    private int flex;
    private int offense;
}
