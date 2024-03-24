package com.project.ase_project.service;

import com.project.ase_project.repository.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.ase_project.model.ddragon.champion.Champion;

import java.util.List;

@Service
public class ChampionService {
    @Autowired
    private ChampionRepository repository;

    public List<Champion> getChampions(){
        return repository.findAll();
    }

    public Champion getChampionById(Integer id){
        return repository.findById(id).orElse(null);
    }
}
