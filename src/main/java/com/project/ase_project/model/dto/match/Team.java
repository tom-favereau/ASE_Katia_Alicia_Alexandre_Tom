package com.project.ase_project.model.dto.match;

import java.util.ArrayList;

/**
 * Represents a TeamDto object from the Riot API (https://developer.riotgames.com/apis#match-v5/GET_getMatch/)
 * This class is used to store the information on a team in a match.
 * This class is used by the Info class as an element of an ArrayList.
 */
public class Team {
    // JSON FIELDS
    private ArrayList<Ban> bans;
    private Objectives objectives;
    private int teamId;
    private boolean win;

    // GETTERS AND SETTERS
    public ArrayList<Ban> getBans() {
        return bans;
    }
    public void setBans(ArrayList<Ban> bans) {
        this.bans = bans;
    }
    public Objectives getObjectives() {
        return objectives;
    }
    public void setObjectives(Objectives objectives) {
        this.objectives = objectives;
    }
    public int getTeamId() {
        return teamId;
    }
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
    public boolean isWin() {
        return win;
    }
    public void setWin(boolean win) {
        this.win = win;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "Team{" +
                "bans=" + bans +
                ", objectives=" + objectives +
                ", teamId=" + teamId +
                ", win=" + win +
                '}';
    }
}
