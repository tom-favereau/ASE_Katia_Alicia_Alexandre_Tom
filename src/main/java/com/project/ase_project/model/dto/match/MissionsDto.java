package com.project.ase_project.model.dto.match;

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
public class MissionsDto {
    private int playerScore0;
    private int playerScore1;
    private int playerScore10;
    private int playerScore11;
    private int playerScore2;
    private int playerScore3;
    private int playerScore4;
    private int playerScore5;
    private int playerScore6;
    private int playerScore7;
    private int playerScore8;
    private int playerScore9;
}
