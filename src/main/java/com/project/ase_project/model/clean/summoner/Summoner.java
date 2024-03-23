package com.project.ase_project.model.clean.summoner;

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
public class Summoner {
    private String name;
    private int profileIconId;
    private long summonerLevel;
    @Id
    private String id;

    public String getProfileIconAddress() {
        return "https://ddragon.leagueoflegends.com/cdn/14.5.1/img/profileicon/" + this.profileIconId + ".png";
    }
}
