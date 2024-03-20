package com.project.ase_project.model.champion;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Champion {
    @JsonProperty
    private String id;
    @JsonProperty("key")
    @Id
    private Integer championKey;
    @JsonProperty
    private String name;
    @JsonProperty
    @Embedded
    private Image image;

    public Champion(){}

    public Champion(String id, Integer championKey, String name, Image image) {
        this.id = id;
        this.championKey = championKey;
        this.name = name;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Champion{" +
                ", id='" + id + '\'' +
                ", key=" + championKey +
                ", name='" + name + '\'' +
                ", image=" + image +
                '}';
    }

    public Integer getChampionKey() {
        return championKey;
    }
}
