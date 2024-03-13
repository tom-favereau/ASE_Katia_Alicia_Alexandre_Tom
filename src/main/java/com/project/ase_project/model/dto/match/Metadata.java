package com.project.ase_project.model.dto.match;

import java.util.ArrayList;

/**
 * Represents an MetadataDto object from the Riot API (https://developer.riotgames.com/apis#match-v5/GET_getMatch/)
 * This class is used to store meta information regarding the game.
 * This class is used by the Match class as an attribute.
 */
public class Metadata {
    // JSON FIELDS
    private String dataVersion;
    private String matchId;
    private ArrayList<String> participants;

    // GETTERS AND SETTERS
    public String getDataVersion() {
        return dataVersion;
    }
    public void setDataVersion(String dataVersion) {
        this.dataVersion = dataVersion;
    }
    public String getMatchId() {
        return matchId;
    }
    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
    public ArrayList<String> getParticipants() {
        return participants;
    }
    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "Metadata{" +
                "dataVersion='" + dataVersion + '\'' +
                ", matchId='" + matchId + '\'' +
                ", participants=" + participants +
                '}';
    }
}
