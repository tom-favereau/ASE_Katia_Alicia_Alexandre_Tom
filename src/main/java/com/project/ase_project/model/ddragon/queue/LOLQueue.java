package com.project.ase_project.model.ddragon.queue;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class LOLQueue {
    @Id
    @JsonProperty
    private Integer queueId;
    @JsonProperty
    private String map;
    @JsonProperty
    private String description = "";
}
