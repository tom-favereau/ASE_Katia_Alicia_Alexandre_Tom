package com.project.ase_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "perkStyleSelectionDto")
public class PerkStyleSelectionDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private int perk;
    private int var1;
    private int var2;
    private int var3;
    @ManyToOne
    @JoinColumn(name = "perkStyleDto", nullable = false)
    private PerkStyleDto perkStyleDto;

    public PerkStyleSelectionDto() {}
}
