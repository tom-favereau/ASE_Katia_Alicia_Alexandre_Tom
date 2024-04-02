package com.project.ase_project.model.ddragon.champion;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString

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
}
