package com.project.ase_project.model.clean.match;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Team {
    @Column(insertable=false, updatable=false)
    private int teamId;
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

    // GETTERS AND SETTERS

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public boolean isFirstBaron() {
        return firstBaron;
    }

    public void setFirstBaron(boolean firstBaron) {
        this.firstBaron = firstBaron;
    }

    public int getBaronKills() {
        return baronKills;
    }

    public void setBaronKills(int baronKills) {
        this.baronKills = baronKills;
    }

    public boolean isFirstChampion() {
        return firstChampion;
    }

    public void setFirstChampion(boolean firstChampion) {
        this.firstChampion = firstChampion;
    }

    public int getChampionKills() {
        return championKills;
    }

    public void setChampionKills(int championKills) {
        this.championKills = championKills;
    }

    public boolean isFirstDragon() {
        return firstDragon;
    }

    public void setFirstDragon(boolean firstDragon) {
        this.firstDragon = firstDragon;
    }

    public int getDragonKills() {
        return dragonKills;
    }

    public void setDragonKills(int dragonKills) {
        this.dragonKills = dragonKills;
    }

    public boolean isFirstHorde() {
        return firstHorde;
    }

    public void setFirstHorde(boolean firstHorde) {
        this.firstHorde = firstHorde;
    }

    public int getHordeKills() {
        return hordeKills;
    }

    public void setHordeKills(int hordeKills) {
        this.hordeKills = hordeKills;
    }

    public boolean isFirstInhibitor() {
        return firstInhibitor;
    }

    public void setFirstInhibitor(boolean firstInhibitor) {
        this.firstInhibitor = firstInhibitor;
    }

    public int getInhibitorKills() {
        return inhibitorKills;
    }

    public void setInhibitorKills(int inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    public boolean isFirstRiftHerald() {
        return firstRiftHerald;
    }

    public void setFirstRiftHerald(boolean firstRiftHerald) {
        this.firstRiftHerald = firstRiftHerald;
    }

    public int getRiftHeraldKills() {
        return riftHeraldKills;
    }

    public void setRiftHeraldKills(int riftHeraldKills) {
        this.riftHeraldKills = riftHeraldKills;
    }

    public boolean isFirstTower() {
        return firstTower;
    }

    public void setFirstTower(boolean firstTower) {
        this.firstTower = firstTower;
    }

    public int getTowerKills() {
        return towerKills;
    }

    public void setTowerKills(int towerKills) {
        this.towerKills = towerKills;
    }
}
