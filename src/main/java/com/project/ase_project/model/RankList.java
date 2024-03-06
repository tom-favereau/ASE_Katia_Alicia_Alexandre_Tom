package com.project.ase_project.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class RankList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "rankList")
    private List<Rank> ranks;
}
