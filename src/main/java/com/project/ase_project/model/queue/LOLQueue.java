package com.project.ase_project.model.queue;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LOLQueue {
    @Id
    @JsonProperty
    private Integer queueId;
    @JsonProperty
    private String map;
    @JsonProperty
    private String description = "";
    public LOLQueue(){}

    public LOLQueue(Integer queueId, String map, String description) {
        this.queueId = queueId;
        this.map = map;
        this.description = description;
    }
}