package com.project.ase_project.model.dto.match;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

/**
 * Represents a PerkStyleDto object from the Riot API (<a href="https://developer.riotgames.com/apis#match-v5/GET_getMatch/">...</a>)
 * This class is used to store information regarding one of the major runes chosen by a player.
 * This class is used by the Participant class as an element of an ArrayList.
 */

@Data
@Getter
@Setter
@ToString
public class PerksDto {
    private StatPerksDto statPerks;
    private ArrayList<StyleDto> styles;
}
