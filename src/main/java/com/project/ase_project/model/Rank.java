package com.project.ase_project.model;

import jakarta.persistence.*;

@Entity
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String leagueId;
    private String summonerId;
    private String summonerName;
    private String queueType;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;
    private boolean hotStreak;
    private boolean veteran;
    private boolean freshBlood;
    private boolean inactive;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "miniSeries_id", referencedColumnName = "id")
    private MiniSeriesDto miniSeries;
    @ManyToOne
    @JoinColumn(name = "rankList_id", nullable = false)
    private RankList rankList;

    public Rank() {}
}
