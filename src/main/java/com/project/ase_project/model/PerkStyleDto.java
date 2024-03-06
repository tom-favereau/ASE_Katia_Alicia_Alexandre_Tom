package com.project.ase_project.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "perkStyleDto")
public class PerkStyleDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String description;
    @OneToMany(mappedBy = "perkStyleDto")
    private Set<PerkStyleSelectionDto> selections;
    private int style;
    @ManyToOne
    @JoinColumn(name = "perksDto_id", nullable = false)
    private PerksDto perksDto;

    public PerkStyleDto() {}
}
