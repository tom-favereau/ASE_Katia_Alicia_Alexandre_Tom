package com.project.ase_project.service;

import com.project.ase_project.model.Summoner;
import com.project.ase_project.repository.SummonerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummonerService {
    @Autowired
    private SummonerRepo repository;

    public Summoner saveUser(Summoner summoner) {
        return repository.save(summoner);
    }

    public List<Summoner> saveFictionalCharacters(List<Summoner> summoners) {
        return repository.saveAll(summoners);
    }

    public List<Summoner> getSummoners() {
        return repository.findAll();
    }

    public Summoner getSummonerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Summoner getSummonerByPpuid(String ppuid) {
        return repository.findByPpuid(ppuid);
    }

    public Summoner getSummonerByUsername(String username) {
        return repository.findByUsername(username);
    }

    public String deleteSummoner(Long id) {
        repository.deleteById(id);
        return "Summoner removed: " + id;
    }

    public Summoner updateUser(Summoner summoner) {
        Summoner existingSummoner = repository.findById(summoner.getId()).orElse(null);
        existingSummoner.setPpuid(summoner.getPpuid());
        existingSummoner.setUsername(summoner.getUsername());
        return repository.save(existingSummoner);
    }


}
