package com.project.ase_project.model.dto.league;

import com.project.ase_project.model.clean.league.League;

/**
 * Represents a LeagueDto object from the Riot API (https://developer.riotgames.com/apis#league-v4/GET_getLeagueEntriesForSummoner)
 * This class is used to store the information on a player's league.
 */
public class LeagueDto {
    // JSON FIELDS
    private String leagueId;
    private String queueType;
    private String tier;
    private String rank;
    private String summonerId;
    private String summonerName;
    private int leaguePoints;
    private int wins;
    private int losses;
    private boolean veteran;
    private boolean inactive;
    private boolean freshBlood;
    private boolean hotStreak;

    // GETTERS AND SETTERS
    public String getLeagueId() {
        return leagueId;
    }
    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }
    public String getQueueType() {
        return queueType;
    }
    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }
    public String getTier() {
        return tier;
    }
    public void setTier(String tier) {
        this.tier = tier;
    }
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getSummonerId() {
        return summonerId;
    }
    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }
    public String getSummonerName() {
        return summonerName;
    }
    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }
    public int getLeaguePoints() {
        return leaguePoints;
    }
    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }
    public int getWins() {
        return wins;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }
    public int getLosses() {
        return losses;
    }
    public void setLosses(int losses) {
        this.losses = losses;
    }
    public boolean isVeteran() {
        return veteran;
    }
    public void setVeteran(boolean veteran) {
        this.veteran = veteran;
    }
    public boolean isInactive() {
        return inactive;
    }
    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }
    public boolean isFreshBlood() {
        return freshBlood;
    }
    public void setFreshBlood(boolean freshBlood) {
        this.freshBlood = freshBlood;
    }
    public boolean isHotStreak() {
        return hotStreak;
    }
    public void setHotStreak(boolean hotStreak) {
        this.hotStreak = hotStreak;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "League{" +
                "leagueId='" + leagueId + '\'' +
                ", queueType='" + queueType + '\'' +
                ", tier='" + tier + '\'' +
                ", rank='" + rank + '\'' +
                ", summonerId='" + summonerId + '\'' +
                ", summonerName='" + summonerName + '\'' +
                ", leaguePoints=" + leaguePoints +
                ", wins=" + wins +
                ", losses=" + losses +
                ", veteran=" + veteran +
                ", inactive=" + inactive +
                ", freshBlood=" + freshBlood +
                ", hotStreak=" + hotStreak +
                '}';
    }

    // TO LEAGUE
    public static League toLeague(LeagueDto leagueDto) {
        League league = new League();
        league.setSummonerName(leagueDto.getSummonerName());
        league.setQueueType(leagueDto.getQueueType());
        league.setTier(leagueDto.getTier());
        league.setRank(leagueDto.getRank());
        league.setLeaguePoints(leagueDto.getLeaguePoints());
        league.setWins(leagueDto.getWins());
        league.setLosses(leagueDto.getLosses());
        return league;
    }
}