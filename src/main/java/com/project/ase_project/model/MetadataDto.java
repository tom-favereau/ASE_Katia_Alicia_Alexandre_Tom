package com.project.ase_project.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "metadataDto")
public class MetadataDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String dataVersion;
    private String matchId;
    @ElementCollection
    private List<String> participants;
    @OneToOne(mappedBy = "metadataDto")
    private Match match;
}
