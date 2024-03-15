package com.project.ase_project.model.dto.league;

import com.project.ase_project.model.clean.league.League;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a LeagueDto object from the Riot API (<a href="https://developer.riotgames.com/apis#league-v4/GET_getLeagueEntriesForSummoner">...</a>)
 * This class is used to store the information on a player's league.
 */

@Data
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
    public static League toLeague(LeagueDto leagueDto) {
        League league = new League();
        league.setLeagueId(leagueDto.getLeagueId());
        league.setSummonerId(leagueDto.getSummonerId());
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