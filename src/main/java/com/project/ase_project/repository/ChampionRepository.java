package com.project.ase_project.repository;

import com.project.ase_project.model.ddragon.champion.Champion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionRepository extends JpaRepository<Champion, Integer> {
}
