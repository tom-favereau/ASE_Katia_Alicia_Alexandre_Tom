package com.project.ase_project.model.clean.summary;

import com.project.ase_project.model.clean.league.League;
import com.project.ase_project.model.clean.summoner.Summoner;

import java.util.ArrayList;

public class Summary {
    public Summary(Summoner summoner, ArrayList<League> leagues) {
        this.summonerId = summoner.getId();
        this.summonerName = summoner.getName();
        this.summonerLevel = summoner.getSummonerLevel();
        this.profileIconId = summoner.getProfileIconId();
        if (leagues.size() != 2) {
            throw new RuntimeException("Invalid number of leagues");
        }
        else if (!leagues.get(0).getQueueType().equals("RANKED_FLEX_SR") || !leagues.get(1).getQueueType().equals("RANKED_SOLO_5x5")) {
            throw new RuntimeException("Invalid queue type");
        } else if (leagues.get(0).getQueueType().equals("RANKED_FLEX_SR") && leagues.get(1).getQueueType().equals("RANKED_FLEX_SR")) {
            throw new RuntimeException("Invalid queue type");
        } else if (leagues.get(0).getQueueType().equals("RANKED_SOLO_5x5") && leagues.get(1).getQueueType().equals("RANKED_SOLO_5x5")) {
            throw new RuntimeException("Invalid queue type");
        } else if (leagues.get(0).getQueueType().equals("RANKED_FLEX_SR") && leagues.get(1).getQueueType().equals("RANKED_SOLO_5x5")) {
            this.rankFlex = leagues.get(0).getTier() + " " + leagues.get(0).getRank() + " " + leagues.get(0).getLeaguePoints() + " LP";
            this.rankSolo = leagues.get(1).getTier() + " " + leagues.get(1).getRank() + " " + leagues.get(1).getLeaguePoints() + " LP";
        } else if (leagues.get(0).getQueueType().equals("RANKED_SOLO_5x5") && leagues.get(1).getQueueType().equals("RANKED_FLEX_SR")) {
            this.rankFlex = leagues.get(1).getTier() + " " + leagues.get(1).getRank() + " " + leagues.get(1).getLeaguePoints() + " LP";
            this.rankSolo = leagues.get(0).getTier() + " " + leagues.get(0).getRank() + " " + leagues.get(0).getLeaguePoints() + " LP";
        } else {
            throw new RuntimeException("Invalid queue type");
        }
        this.region = "EUW1";
        this.grade = 0;
    }

    // JSON FIELDS
    private String summonerId;
    private String summonerName;
    private long summonerLevel;
    private int profileIconId;
    private String rankFlex;
    private String rankSolo;
    private String region;
    private int grade;

    // GETTERS AND SETTERS

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

    public long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public String getRankFlex() {
        return rankFlex;
    }

    public void setRankFlex(String rankFlex) {
        this.rankFlex = rankFlex;
    }

    public String getRankSolo() {
        return rankSolo;
    }

    public void setRankSolo(String rankSolo) {
        this.rankSolo = rankSolo;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
