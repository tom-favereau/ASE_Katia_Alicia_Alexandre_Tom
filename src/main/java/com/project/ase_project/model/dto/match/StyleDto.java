package com.project.ase_project.model.dto.match;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

/**
 * Represents an PerkStyleDto object from the Riot API (<a href="https://developer.riotgames.com/apis#match-v5/GET_getMatch/">...</a>)
 * This class is used to store the information on the primary and secondary runes chosen by a player.
 * This class is used by the Perks class as an element of an ArrayList.
 */

@Data
@Getter
@Setter
@ToString
public class StyleDto {
    private String description;
    private ArrayList<SelectionDto> selections;
    private int style;
}
