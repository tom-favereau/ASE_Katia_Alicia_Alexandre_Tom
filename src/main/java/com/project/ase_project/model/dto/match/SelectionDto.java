package com.project.ase_project.model.dto.match;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents an PerkStyleSelectionDto object from the Riot API (<a href="https://developer.riotgames.com/apis#match-v5/GET_getMatch/">...</a>)
 * This class is used to store the information on a certain rune.
 * This class is used by the Style class as an element of an ArrayList.
 */

@Data
@Getter
@Setter
@ToString
public class SelectionDto {
    private int perk;
    private int var1;
    private int var2;
    private int var3;
}
