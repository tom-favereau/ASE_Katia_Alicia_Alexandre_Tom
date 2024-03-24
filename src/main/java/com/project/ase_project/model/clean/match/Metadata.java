package com.project.ase_project.model.clean.match;

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
public class Metadata {
    // Date fields
    private Long gameCreation;
    private int gameDuration;
    private Long gameEndTimestamp;

    // Meta fields
    private String dataVersion;
    private Long gameId;
    private String platformId;

    // Mode fields
    private String gameMode;
    private String gameName;
    private String gameType;
    private String gameVersion;
    private int mapId;
    private int queueId;
}
