package com.project.ase_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "objectivesDto")
public class ObjectivesDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "baron_id", referencedColumnName = "id")
    private ObjectiveDto baron;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "champion_id", referencedColumnName = "id")
    private ObjectiveDto champion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dragon_id", referencedColumnName = "id")
    private ObjectiveDto dragon;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inhibitor_id", referencedColumnName = "id")
    private ObjectiveDto inhibitor;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "riftHerald_id", referencedColumnName = "id")
    private ObjectiveDto riftHerald;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tower_id", referencedColumnName = "id")
    private ObjectiveDto tower;
}
