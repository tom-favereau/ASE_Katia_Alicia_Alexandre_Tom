package com.project.ase_project.model.dto.match;

import java.util.ArrayList;

/**
 * Represents a PerkStyleDto object from the Riot API (https://developer.riotgames.com/apis#match-v5/GET_getMatch/)
 * This class is used to store information regarding one of the major runes chosen by a player.
 * This class is used by the Participant class as an element of an ArrayList.
 */
public class Perks {
    // JSON FIELDS
    private StatPerks statPerks;
    private ArrayList<Style> styles;

    // GETTERS AND SETTERS
    public StatPerks getStatPerks() {
        return statPerks;
    }
    public void setStatPerks(StatPerks statPerks) {
        this.statPerks = statPerks;
    }
    public ArrayList<Style> getStyles() {
        return styles;
    }
    public void setStyles(ArrayList<Style> styles) {
        this.styles = styles;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "Perks{" +
                "statPerks=" + statPerks +
                ", styles=" + styles +
                '}';
    }
}
