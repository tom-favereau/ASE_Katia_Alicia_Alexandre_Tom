package com.project.ase_project.model.clean.match;

import jakarta.persistence.Column;
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
public class Team {
    @Column(insertable=false, updatable=false)
    @NonNull
    private Integer teamId;
    private boolean win;
    private boolean firstBaron;
    private int baronKills;
    private boolean firstChampion;
    private int championKills;
    private boolean firstDragon;
    private int dragonKills;
    private boolean firstHorde;
    private int hordeKills;
    private boolean firstInhibitor;
    private int inhibitorKills;
    private boolean firstRiftHerald;
    private int riftHeraldKills;
    private boolean firstTower;
    private int towerKills;
}
