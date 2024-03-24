package com.project.ase_project.repository;

import com.project.ase_project.model.ddragon.queue.LOLQueue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueRepository extends JpaRepository<LOLQueue, Integer> {
}
