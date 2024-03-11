package com.project.ase_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ase_project.model.League;

public interface RankRepository extends JpaRepository<League, Long> {

}
