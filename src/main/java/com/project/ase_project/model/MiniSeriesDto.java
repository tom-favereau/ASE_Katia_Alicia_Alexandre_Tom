package com.project.ase_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "miniSeries")
public class MiniSeriesDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private int losses;
    private String progress;
    private int target;
    private int wins;
    @OneToOne(mappedBy = "miniSeries")
    private Rank rank;
}
