package com.project.ase_project.model.ddragon.champion;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
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
}
