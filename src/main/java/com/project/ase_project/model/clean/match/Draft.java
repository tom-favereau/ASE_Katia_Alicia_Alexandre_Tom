package com.project.ase_project.model.clean.match;

import jakarta.persistence.Column;
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
public class Draft {
    @Column(insertable=false, updatable=false)
    @NonNull
    private Integer teamId;
    private int firstBan;
    private int secondBan;
    private int thirdBan;
    private int fourthBan;
    private int fifthBan;
    private int firstPick;
    private int secondPick;
    private int thirdPick;
    private int fourthPick;
    private int fifthPick;
}
