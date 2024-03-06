package com.project.ase_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "objectiveDto")
public class ObjectiveDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private boolean first;
    private int kills;
}
