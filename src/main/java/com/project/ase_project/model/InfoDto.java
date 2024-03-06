package com.project.ase_project.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "infoDto")
public class InfoDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private long gameCreation;
    private long gameDuration;
    private long gameEndTimestamp;
    private long gameId;
    private String gameMode;
    private String gameName;
    private long gameStartTimestamp;
    private String gameType;
    private String gameVersion;
    private int mapId;
    @OneToMany(mappedBy = "infoDto")
    private Set<ParticipantDto> participants;
    private String platformId;
    private int queueId;
    @OneToMany(mappedBy = "infoDto")
    private Set<TeamDto> teams;
    private String tournamentCode;
    @OneToOne(mappedBy = "infoDto")
    private Match match;
}
