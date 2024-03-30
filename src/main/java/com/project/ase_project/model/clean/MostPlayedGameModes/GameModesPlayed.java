package com.project.ase_project.model.clean.MostPlayedGameModes;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GameModesPlayed {
    // Summoner fields
    private String summonerId;
    private String summonerName;

    private int TotalGamesPlayed;
    private int TotalWins;
    private int TotalLosses;
    private float WinRate;

    private int TotalKills;
    private int TotalDeaths;
    private int TotalAssists;
    private float KDA;

    // Most Played Game Mode fields
    private int MostPlayedGameModeId;
    private String MostPlayedGameModeName;

    private int MostPlayedGameModeCount;
    private int MostPlayedGameModeWins;
    private int MostPlayedGameModeLosses;
    private float MostPlayedGameModeWinRate;

    private int MostPlayedGameModeKills;
    private int MostPlayedGameModeDeaths;
    private int MostPlayedGameModeAssists;
    private float MostPlayedGameModeKDA;

    // Second Most Played Game Mode fields
    private int SecondMostPlayedGameModeId;
    private String SecondMostPlayedGameModeName;

    private int SecondMostPlayedGameModeCount;
    private int SecondMostPlayedGameModeWins;
    private int SecondMostPlayedGameModeLosses;
    private float SecondMostPlayedGameModeWinRate;

    private int SecondMostPlayedGameModeKills;
    private int SecondMostPlayedGameModeDeaths;
    private int SecondMostPlayedGameModeAssists;
    private float SecondMostPlayedGameModeKDA;

    // Best Performing Game Mode fields
    private int BestPerformingGameModeId;
    private String BestPerformingGameModeName;

    private int BestPerformingGameModeCount;
    private int BestPerformingGameModeWins;
    private int BestPerformingGameModeLosses;
    private float BestPerformingGameModeWinRate;

    private int BestPerformingGameModeKills;
    private int BestPerformingGameModeDeaths;
    private int BestPerformingGameModeAssists;
    private float BestPerformingGameModeKDA;

    // Worst Performing Game Mode fields
    private int WorstPerformingGameModeId;
    private String WorstPerformingGameModeName;

    private int WorstPerformingGameModeCount;
    private int WorstPerformingGameModeWins;
    private int WorstPerformingGameModeLosses;
    private float WorstPerformingGameModeWinRate;

    private int WorstPerformingGameModeKills;
    private int WorstPerformingGameModeDeaths;
    private int WorstPerformingGameModeAssists;
    private float WorstPerformingGameModeKDA;
}
