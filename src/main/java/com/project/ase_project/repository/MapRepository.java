package com.project.ase_project.repository;

import com.project.ase_project.model.maps.LOLMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<LOLMap, Integer> {
}
