package com.project.ase_project.repository;

import com.project.ase_project.model.champion.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChampionRepository extends JpaRepository<Champion, Integer> {
}
