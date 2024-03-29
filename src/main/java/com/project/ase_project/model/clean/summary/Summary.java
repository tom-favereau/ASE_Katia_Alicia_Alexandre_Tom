package com.project.ase_project.model.clean.summary;

import com.project.ase_project.model.clean.league.League;
import com.project.ase_project.model.clean.summoner.Summoner;
import lombok.*;

import java.util.ArrayList;

@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Summary {
    public Summary(Summoner summoner, ArrayList<League> leagues) {
        this.summonerId = summoner.getId();
        this.summonerName = summoner.getName();
        this.summonerLevel = summoner.getSummonerLevel();
        this.profileIconId = summoner.getProfileIconId();
        this.average = summoner.getAverage();
        this.cardinal = summoner.getCardinal();
        if (leagues.isEmpty()) {
            this.rankFlex = "UNRANKED";
            this.rankSolo = "UNRANKED";
        } else if (leagues.size() != 2) {
            throw new RuntimeException("Invalid number of leagues");
        } else if (leagues.get(0).getQueueType().equals("RANKED_FLEX_SR") && leagues.get(1).getQueueType().equals("RANKED_SOLO_5x5")) {
            this.rankFlex = leagues.get(0).getTier() + " " + leagues.get(0).getRank() + " " + leagues.get(0).getLeaguePoints() + " LP" + " " + leagues.get(0).getWins() + "W / " + leagues.get(0).getLosses() + "L";
            this.rankSolo = leagues.get(1).getTier() + " " + leagues.get(1).getRank() + " " + leagues.get(1).getLeaguePoints() + " LP" + " " + leagues.get(1).getWins() + "W / " + leagues.get(1).getLosses() + "L";
        } else if (leagues.get(0).getQueueType().equals("RANKED_SOLO_5x5") && leagues.get(1).getQueueType().equals("RANKED_FLEX_SR")) {
            this.rankFlex = leagues.get(1).getTier() + " " + leagues.get(1).getRank() + " " + leagues.get(1).getLeaguePoints() + " LP" + " " + leagues.get(1).getWins() + "W / " + leagues.get(1).getLosses() + "L";
            this.rankSolo = leagues.get(0).getTier() + " " + leagues.get(0).getRank() + " " + leagues.get(0).getLeaguePoints() + " LP" + " " + leagues.get(0).getWins() + "W / " + leagues.get(0).getLosses() + "L";
        } else {
            throw new RuntimeException("Invalid queue type");
        }
        this.region = "EUW1";
    }

    // JSON FIELDS
    private String summonerId;
    private String summonerName;
    private long summonerLevel;
    private int profileIconId;
    private String rankFlex;
    private String rankSolo;
    private String region;
    private float average;
    private int cardinal;
}
