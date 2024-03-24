package com.project.ase_project.model.dto.match;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a BanDto object from the Riot API (<a href="https://developer.riotgames.com/apis#match-v5/GET_getMatch/">...</a>)
 * This class is used to store the ban information of a match, including the champion's id in Riot's static database
 * (<a href="https://ddragon.leagueoflegends.com/cdn/14.5.1/data/en_US/champion.json">...</a>) and the ban position.
 * This class is used by the TeamDto class as an element in an ArrayList.
 */

@Data
@Getter
@Setter
@ToString
public class BanDto {
    private int championId;
    private int pickTurn;
}