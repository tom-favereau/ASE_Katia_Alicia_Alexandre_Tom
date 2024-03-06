package com.project.ase_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "perkStatsDto")
public class PerkStatsDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private int defense;
    private int flex;
    private int offense;
    @OneToOne(mappedBy = "statPerks")
    private PerksDto perksDto;
}
