package com.project.ase_project.model.dto.match;

/**
 * Represents an ObjectiveDto object from the Riot API (https://developer.riotgames.com/apis#match-v5/GET_getMatch/)
 * This class is used to store information regarding the champions in the game, including whether this team killed the
 * first champion and the number of time this teams has killed champions.
 * This class is used by the Objectives class as an attribute.
 */
public class Champion {
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
        return "Champion{" +
                "first=" + first +
                ", kills=" + kills +
                '}';
    }
}
