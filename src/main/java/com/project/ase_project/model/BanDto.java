package com.project.ase_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "banDto")
public class BanDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private int championId;
    private int pickTurn;
    @ManyToOne
    @JoinColumn(name = "teamDto_id", nullable = false)
    private TeamDto teamDto;

    public BanDto() {}
}
