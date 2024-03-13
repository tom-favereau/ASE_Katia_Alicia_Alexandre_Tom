package com.project.ase_project.model.dto.match;

/**
 * Represents an ObjectiveDto object from the Riot API (https://developer.riotgames.com/apis#match-v5/GET_getMatch/)
 * This class is used to store information regarding the Baron Nashor objective, including whether this team killed it
 * for the fist time and the number of time this teams has killed this objective.
 * This class is used by the Objectives class as an attribute.
 */
public class Baron{
    // JSON FIELDS
    private boolean first;
    private int kills;

    // GETTERS AND SETTERS
    public boolean isFirst() {
        return first;
    }
    public void setFirst(boolean first) {
        this.first = first;
    }
    public int getKills() {
        return kills;
    }
    public void setKills(int kills) {
        this.kills = kills;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "Baron{" +
                "first=" + first +
                ", kills=" + kills +
                '}';
    }
}
