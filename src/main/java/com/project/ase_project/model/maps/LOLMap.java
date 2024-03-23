package com.project.ase_project.model.maps;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LOLMap {
    @Id
    @JsonProperty
    private Integer mapId;
    @JsonProperty
    String mapName;
    @JsonProperty
    private String notes;

    public LOLMap(){}

    public LOLMap(Integer mapId, String mapName, String notes) {
        this.mapId = mapId;
        this.mapName = mapName;
        this.notes = notes;
    }
}
