package com.project.ase_project.model.champion;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Champion {

    @JsonProperty
    private String version;
    @JsonProperty
    @Id
    private String id;
    @JsonProperty("key")
    private Integer championKey;
    @JsonProperty
    private String name;
    @JsonProperty
    private String title;
    @JsonProperty
    @Embedded
    private Info info;
    @JsonProperty
    @Embedded
    private Image image;
    @JsonProperty
    @ElementCollection
    private List<String> tags;
    @JsonProperty
    private String partype;
    @JsonProperty
    private Stats stats;

    public Champion(){}

    @Override
    public String toString() {
        return "Champion{" +
                "version='" + version + '\'' +
                ", id='" + id + '\'' +
                ", key=" + championKey +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", info=" + info +
                ", image=" + image +
                ", tags=" + tags +
                ", partype='" + partype + '\'' +
                ", stats=" + stats +
                '}';
    }

    public Integer getChampionKey() {
        return championKey;
    }
}
