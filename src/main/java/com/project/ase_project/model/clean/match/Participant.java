package com.project.ase_project.model.clean.match;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Participant {
    // Identity fields
    private String puuid;
    private String summonerId;
    private String summonerName;
    private int summonerLevel;
    private int participantId;
    private String riotIdGameName;
    private String riotIdTagLine;

    // Game fields
    private int teamId;
    private String role;
    private String lane;
    private String teamPosition;
    private String individualPosition;
    private int championId;
    private String championName;

    // Performance fields
    private boolean win;
    private int kills;
    private int deaths;
    private int assists;
    private int visionScore;
}
