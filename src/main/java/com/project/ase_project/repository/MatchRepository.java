package com.project.ase_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.ase_project.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {

}
