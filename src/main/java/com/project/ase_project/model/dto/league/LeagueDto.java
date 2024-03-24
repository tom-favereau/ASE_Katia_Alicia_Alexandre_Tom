package com.project.ase_project.model.dto.league;

import com.project.ase_project.model.clean.league.League;
import lombok.*;

/**
 * Represents a LeagueDto object from the Riot API (<a href="https://developer.riotgames.com/apis#league-v4/GET_getLeagueEntriesForSummoner">...</a>)
 * This class is used to store the information on a player's league.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
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

    // TO LEAGUE
    public League toLeague() {
        League league = new League();
        league.setLeagueId(this.getLeagueId());
        league.setSummonerId(this.getSummonerId());
        league.setSummonerName(this.getSummonerName());
        league.setQueueType(this.getQueueType());
        league.setTier(this.getTier());
        league.setRank(this.getRank());
        league.setLeaguePoints(this.getLeaguePoints());
        league.setWins(this.getWins());
        league.setLosses(this.getLosses());
        return league;
    }
}