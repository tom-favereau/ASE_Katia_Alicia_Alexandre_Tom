package com.project.ase_project.model.ddragon.maps;

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
public class LOLMap {
    @Id
    @JsonProperty
    private Integer mapId;
    @JsonProperty
    String mapName;
    @JsonProperty
    private String notes;
}
