package com.project.ase_project.model.dto.match;

/**
 * Represents a BanDto object from the Riot API (https://developer.riotgames.com/apis#match-v5/GET_getMatch/)
 * This class is used to store the ban information of a match, including the champion's id in Riot's static database
 * (https://ddragon.leagueoflegends.com/cdn/14.5.1/data/en_US/champion.json) and the ban position.
 * This class is used by the TeamDto class as an element in an ArrayList.
 */
public class Ban{
    // JSON FIELDS
    private int championId;
    private int pickTurn;

    // GETTERS AND SETTERS
    public int getChampionId() {
        return championId;
    }
    public void setChampionId(int championId) {
        this.championId = championId;
    }
    public int getPickTurn() {
        return pickTurn;
    }
    public void setPickTurn(int pickTurn) {
        this.pickTurn = pickTurn;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "Ban{" +
                "championId=" + championId +
                ", pickTurn=" + pickTurn +
                '}';
    }
}