package com.project.ase_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ase_project.model.clean.league.League;

public interface LeagueRepository extends JpaRepository<League, String> {

}
