package com.project.ase_project.model.dto.summoner;

import com.project.ase_project.model.clean.summoner.Summoner;
import lombok.*;

/**
 * Represents a SummonerDto object from the Riot API (<a href="https://developer.riotgames.com/apis#summoner-v4/GET_getBySummonerName">...</a>)
 * This class is used to store all the information regarding a summoner.
 * This class is the root of all other classes in this package.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class SummonerDto {
    private String id;
    private String accountId;
    private String puuid;
    private String name;
    private int profileIconId;
    private long revisionDate;
    private long summonerLevel;

    // TO SUMMONER
    public Summoner toSummoner() {
        Summoner summoner = new Summoner();
        summoner.setName(this.getName());
        summoner.setProfileIconId(this.getProfileIconId());
        summoner.setSummonerLevel(this.getSummonerLevel());
        summoner.setId(this.getId());
        return summoner;
    }
}
