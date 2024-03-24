package com.project.ase_project.repository;

import com.project.ase_project.model.clean.grade.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, String> {
}
