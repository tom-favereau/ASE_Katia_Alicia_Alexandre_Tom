package com.project.ase_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.ase_project.model.Summoner;

public interface SummonerRepository extends JpaRepository<Summoner, String> {

}
