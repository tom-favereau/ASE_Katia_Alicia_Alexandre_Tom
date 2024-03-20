package com.project.ase_project.controller;

import com.project.ase_project.model.champion.Champion;
import com.project.ase_project.service.ChampionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/riot")
public class ChampionController {

    @Autowired
    private ChampionService service;
    @GetMapping("/champions")
    public List<Champion> findAllChampions(){
        return service.getChampions();
    }
    @GetMapping("/champions/{id}")
    public Champion findChampionById(@PathVariable Integer id){
        return service.getChampionById(id);
    }
}
