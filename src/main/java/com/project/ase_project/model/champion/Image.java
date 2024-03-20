package com.project.ase_project.model.champion;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

@Embeddable
public class Image{
    @JsonProperty("full")
    private String fullImage;
    @JsonProperty
    private String sprite;
    @JsonProperty("group")
    private String imageGroup;
    @JsonProperty
    private int x;
    @JsonProperty
    private int y;
    @JsonProperty
    private int w;
    @JsonProperty
    private int h;

    public Image(){}
}
