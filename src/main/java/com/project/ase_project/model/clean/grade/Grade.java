package com.project.ase_project.model.clean.grade;

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
public class Grade {
    @Id
    private String id;
    private String summonerName;
    private float average;
    private int cardinal;
}
