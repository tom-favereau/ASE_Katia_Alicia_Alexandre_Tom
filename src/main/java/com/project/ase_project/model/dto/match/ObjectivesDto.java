package com.project.ase_project.model.dto.match;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a ObjectivesDto object from the Riot API (<a href="https://developer.riotgames.com/apis#match-v5/GET_getMatch/">...</a>)
 * This class is used to store the information regarding every objective.
 * This class is used by the TeamDto as an attribute.
 */

@Data
@Getter
@Setter
@ToString
public class ObjectivesDto {
    private BaronDto baron;
    private ChampionDto champion;
    private DragonDto dragon;
    private HordeDto horde;
    private InhibitorDto inhibitor;
    private RiftHeraldDto riftHerald;
    private TowerDto tower;
}
