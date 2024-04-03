package com.project.ase_project.model.dto.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a MissionDto object from the Riot API that is not actually identified or mentioned in the API documentation.
 * This class is used to store information regarding the missions accomplished in a game.
 * This class is used by the Participant class as an attribute.
 */

@Data
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class MissionsDto {
    private int PlayerScore0;
    private int PlayerScore1;
    //private int PlayerScore10;
    //private int PlayerScore11;
    private int PlayerScore2;
    private int PlayerScore3;
    private int PlayerScore4;
    private int PlayerScore5;
    private int PlayerScore6;
    private int PlayerScore7;
    private int PlayerScore8;
    private int PlayerScore9;
}
