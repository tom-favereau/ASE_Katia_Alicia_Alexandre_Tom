package com.project.ase_project.model.clean.MostPlayedChampions;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ChampionsPlayed {
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

    // Most Played Champion fields
    private int MostPlayedChampionId;
    private String MostPlayedChampionName;

    private int MostPlayedChampionCount;
    private int MostPlayedChampionWins;
    private int MostPlayedChampionLosses;
    private float MostPlayedChampionWinRate;

    private int MostPlayedChampionKills;
    private int MostPlayedChampionDeaths;
    private int MostPlayedChampionAssists;
    private float MostPlayedChampionKDA;

    // Second Most Played Champion fields
    private int SecondMostPlayedChampionId;
    private String SecondMostPlayedChampionName;

    private int SecondMostPlayedChampionCount;
    private int SecondMostPlayedChampionWins;
    private int SecondMostPlayedChampionLosses;
    private float SecondMostPlayedChampionWinRate;

    private int SecondMostPlayedChampionKills;
    private int SecondMostPlayedChampionDeaths;
    private int SecondMostPlayedChampionAssists;
    private float SecondMostPlayedChampionKDA;

    // Best Performing Champion fields
    private int BestPerformingChampionId;
    private String BestPerformingChampionName;

    private int BestPerformingChampionCount;
    private int BestPerformingChampionWins;
    private int BestPerformingChampionLosses;
    private float BestPerformingChampionWinRate;

    private int BestPerformingChampionKills;
    private int BestPerformingChampionDeaths;
    private int BestPerformingChampionAssists;
    private float BestPerformingChampionKDA;

    // Worst Performing Champion fields
    private int WorstPerformingChampionId;
    private String WorstPerformingChampionName;

    private int WorstPerformingChampionCount;
    private int WorstPerformingChampionWins;
    private int WorstPerformingChampionLosses;
    private float WorstPerformingChampionWinRate;

    private int WorstPerformingChampionKills;
    private int WorstPerformingChampionDeaths;
    private int WorstPerformingChampionAssists;
    private float WorstPerformingChampionKDA;

    public String getMPProfileIconAddress() {
        return "https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/" + MostPlayedChampionName + ".png";
    }

    public String getSMPProfileIconAddress() {
        return "https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/" + SecondMostPlayedChampionName + ".png";
    }

    public String getBPProfileIconAddress() {
        return "https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/" + BestPerformingChampionName + ".png";
    }

    public String getWPProfileIconAddress() {
        return "https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/" + WorstPerformingChampionName + ".png";
    }
}
