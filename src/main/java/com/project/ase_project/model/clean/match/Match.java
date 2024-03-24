package com.project.ase_project.model.clean.match;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Match {
    // JSON FIELDS
    @Id
    private String matchId;
    @Embedded
    private Metadata metadata;
    @ElementCollection
    private List<Participant> participants;
    @ElementCollection
    private List<Team> teams;
    @ElementCollection
    private List<Draft> drafts;
}
