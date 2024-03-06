package com.project.ase_project.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "perksDto")
public class PerksDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "perkStatsDto_id", referencedColumnName = "id")
    private PerkStatsDto statPerks;
    @OneToMany(mappedBy = "perksDto")
    private Set<PerkStyleDto> styles;
    @OneToOne(mappedBy = "perks")
    private ParticipantDto participantDto;
}
