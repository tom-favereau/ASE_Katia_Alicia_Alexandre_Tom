package com.project.ase_project.model.dto.match;

/**
 * Represents a ObjectivesDto object from the Riot API (https://developer.riotgames.com/apis#match-v5/GET_getMatch/)
 * This class is used to store the information regarding every objective.
 * This class is used by the TeamDto as an attribute.
 */
public class Objectives {
    // JSON FIELDS
    private Baron baron;
    private Champion champion;
    private Dragon dragon;
    private Horde horde;
    private Inhibitor inhibitor;
    private RiftHerald riftHerald;
    private Tower tower;

    // GETTERS AND SETTERS
    public Baron getBaron() {
        return baron;
    }
    public void setBaron(Baron baron) {
        this.baron = baron;
    }
    public Champion getChampion() {
        return champion;
    }
    public void setChampion(Champion champion) {
        this.champion = champion;
    }
    public Dragon getDragon() {
        return dragon;
    }
    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }
    public Horde getHorde() {
        return horde;
    }
    public void setHorde(Horde horde) {
        this.horde = horde;
    }
    public Inhibitor getInhibitor() {
        return inhibitor;
    }
    public void setInhibitor(Inhibitor inhibitor) {
        this.inhibitor = inhibitor;
    }
    public RiftHerald getRiftHerald() {
        return riftHerald;
    }
    public void setRiftHerald(RiftHerald riftHerald) {
        this.riftHerald = riftHerald;
    }
    public Tower getTower() {
        return tower;
    }
    public void setTower(Tower tower) {
        this.tower = tower;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "Objectives{" +
                "baron=" + baron +
                ", champion=" + champion +
                ", dragon=" + dragon +
                ", horde=" + horde +
                ", inhibitor=" + inhibitor +
                ", riftHerald=" + riftHerald +
                ", tower=" + tower +
                '}';
    }
}
