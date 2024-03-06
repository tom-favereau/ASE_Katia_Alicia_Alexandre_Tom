package com.project.ase_project.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "teamDto")
public class TeamDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToMany(mappedBy = "teamDto")
    private Set<BanDto> bans;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "objectiveDto_id", referencedColumnName = "id")
    private ObjectivesDto objectives;
    private int teamId;
    private boolean win;

    @ManyToOne
    @JoinColumn(name = "infoDto_id", nullable = false)
    private InfoDto infoDto;

    public TeamDto() {}
}
