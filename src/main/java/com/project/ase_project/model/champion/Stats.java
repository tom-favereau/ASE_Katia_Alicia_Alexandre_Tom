package com.project.ase_project.model.champion;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

@Embeddable
public class Stats{
    @JsonProperty
    private int hp;
    @JsonProperty
    private int hpperlevel;
    @JsonProperty
    private int mp;
    @JsonProperty
    private int mpperlevel;
    @JsonProperty
    private int movespeed;
    @JsonProperty
    private int armor;
    @JsonProperty
    private double armorperlevel;
    @JsonProperty
    private int spellblock;
    @JsonProperty
    private double spellblockperlevel;
    @JsonProperty
    private int attackrange;
    @JsonProperty
    private int hpregen;
    @JsonProperty
    private int hpregenperlevel;
    @JsonProperty
    private int mpregen;
    @JsonProperty
    private int mpregenperlevel;
    @JsonProperty
    private int crit;
    @JsonProperty
    private int critperlevel;
    @JsonProperty
    private int attackdamage;
    @JsonProperty
    private int attackdamageperlevel;
    @JsonProperty
    private double attackspeedperlevel;
    @JsonProperty
    private double attackspeed;

    public Stats(){}
}