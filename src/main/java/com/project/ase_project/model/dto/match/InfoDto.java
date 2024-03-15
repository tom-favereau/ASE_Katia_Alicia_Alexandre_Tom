package com.project.ase_project.model.dto.match;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

/**
 * Represents an InfoDto object from the Riot API (<a href="https://developer.riotgames.com/apis#match-v5/GET_getMatch/">...</a>)
 * This class is used to store general information regarding the game.
 * This class is used by the Match class as an attribute.
 */

@Data
@Getter
@Setter
@ToString
public class InfoDto {
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
    private ArrayList<ParticipantDto> participants;
    private String platformId;
    private int queueId;
    private ArrayList<TeamDto> teams;
    private String tournamentCode;
}
