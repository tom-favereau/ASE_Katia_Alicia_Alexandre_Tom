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
@EqualsAndHashCode
public class Summoner {
    private String name;
    private int profileIconId;
    private long summonerLevel;
    private float average;
    private int cardinal;
    private String puuid;
    @Id
    private String id;
}
