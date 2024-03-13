package com.project.ase_project.model.champion;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Champion {
    @JsonProperty
    @Id
    private String id;
    @JsonProperty("key")
    private Integer championKey;
    @JsonProperty
    private String name;
    @JsonProperty
    @Embedded
    private Image image;

    public Champion(){}

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
