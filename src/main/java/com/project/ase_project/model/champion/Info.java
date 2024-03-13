package com.project.ase_project.model.champion;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

@Embeddable
public class Info{
    @JsonProperty
    private int attack;
    @JsonProperty
    private int defense;
    @JsonProperty
    private int magic;
    @JsonProperty
    private int difficulty;

    public Info(){}
}
