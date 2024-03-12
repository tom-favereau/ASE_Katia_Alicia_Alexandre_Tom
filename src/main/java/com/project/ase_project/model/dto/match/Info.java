package com.project.ase_project.model.dto.match;

import java.util.ArrayList;

/**
 * Represents an InfoDto object from the Riot API (https://developer.riotgames.com/apis#match-v5/GET_getMatch/)
 * This class is used to store general information regarding the game.
 * This class is used by the Match class as an attribute.
 */
public class Info {
    // JSON FIELDS
    private long gameCreation;
    private int gameDuration;
    private long gameEndTimestamp;
    private long gameId;
    private String gameMode;
    private String gameName;
    private long gameStartTimestamp;
    private String gameType;
    private String gameVersion;
    private int mapId;
    private ArrayList<Participant> participants;
    private String platformId;
    private int queueId;
    private ArrayList<Team> teams;
    private String tournamentCode;

    // GETTERS AND SETTERS
    public long getGameCreation() {
        return gameCreation;
    }
    public void setGameCreation(long gameCreation) {
        this.gameCreation = gameCreation;
    }
    public int getGameDuration() {
        return gameDuration;
    }
    public void setGameDuration(int gameDuration) {
        this.gameDuration = gameDuration;
    }
    public long getGameEndTimestamp() {
        return gameEndTimestamp;
    }
    public void setGameEndTimestamp(long gameEndTimestamp) {
        this.gameEndTimestamp = gameEndTimestamp;
    }
    public long getGameId() {
        return gameId;
    }
    public void setGameId(long gameId) {
        this.gameId = gameId;
    }
    public String getGameMode() {
        return gameMode;
    }
    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }
    public String getGameName() {
        return gameName;
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public long getGameStartTimestamp() {
        return gameStartTimestamp;
    }
    public void setGameStartTimestamp(long gameStartTimestamp) {
        this.gameStartTimestamp = gameStartTimestamp;
    }
    public String getGameType() {
        return gameType;
    }
    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
    public String getGameVersion() {
        return gameVersion;
    }
    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }
    public int getMapId() {
        return mapId;
    }
    public void setMapId(int mapId) {
        this.mapId = mapId;
    }
    public ArrayList<Participant> getParticipants() {
        return participants;
    }
    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }
    public String getPlatformId() {
        return platformId;
    }
    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }
    public int getQueueId() {
        return queueId;
    }
    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }
    public ArrayList<Team> getTeams() {
        return teams;
    }
    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
    public String getTournamentCode() {
        return tournamentCode;
    }
    public void setTournamentCode(String tournamentCode) {
        this.tournamentCode = tournamentCode;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "Info{" +
                "gameCreation=" + gameCreation +
                ", gameDuration=" + gameDuration +
                ", gameEndTimestamp=" + gameEndTimestamp +
                ", gameId=" + gameId +
                ", gameMode='" + gameMode + '\'' +
                ", gameName='" + gameName + '\'' +
                ", gameStartTimestamp=" + gameStartTimestamp +
                ", gameType='" + gameType + '\'' +
                ", gameVersion='" + gameVersion + '\'' +
                ", mapId=" + mapId +
                ", participants=" + participants +
                ", platformId='" + platformId + '\'' +
                ", queueId=" + queueId +
                ", teams=" + teams +
                ", tournamentCode='" + tournamentCode + '\'' +
                '}';
    }
}
