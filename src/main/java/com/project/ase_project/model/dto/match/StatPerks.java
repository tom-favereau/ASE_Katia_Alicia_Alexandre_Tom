package com.project.ase_project.model.dto.match;

/**
 * Represents an PerkStatsDto object from the Riot API (https://developer.riotgames.com/apis#match-v5/GET_getMatch/)
 * This class is used to store the information on the minor stat runes.
 * This class is used by the Perks class as an attribute.
 */
public class StatPerks {
    // JSON FIELDS
    private int defense;
    private int flex;
    private int offense;

    // GETTERS AND SETTERS
    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int getFlex() {
        return flex;
    }
    public void setFlex(int flex) {
        this.flex = flex;
    }
    public int getOffense() {
        return offense;
    }
    public void setOffense(int offense) {
        this.offense = offense;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "StatPerks{" +
                "defense=" + defense +
                ", flex=" + flex +
                ", offense=" + offense +
                '}';
    }
}
