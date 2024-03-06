package com.project.ase_project.repository;
import com.project.ase_project.model.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummonerRepo extends JpaRepository<Summoner, Long> {
    Summoner findByPpuid(String ppuid);
    Summoner findByUsername(String username);
}

