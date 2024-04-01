package com.project.ase_project.controller;

import com.project.ase_project.model.ddragon.champion.Champion;
import com.project.ase_project.service.ChampionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Tag(name = "Champions", description = "Methods for Champion APIs")
    @Operation(summary = "Get the list of all champions", description = "Get the list of all champions" +
            " as of patch 14.5.")
    public List<Champion> findAllChampions(){
        return service.getChampions();
    }
    @GetMapping("/champions/{id}")
    @Tag(name = "Champions")
    @Operation(summary = "Get a champion's info", description = "Get a champion's info given by key, as of patch 14.5.1: " +
            "id, name, image information and key.")
    public Champion findChampionById(@PathVariable Integer id){
        Champion res = service.getChampionById(id);
        if (res == null){
            throw new IllegalArgumentException("Erreur 400 : l'id n'est pas correct.");
        } else {
            return res;
        }
    }

    @GetMapping("/champion/")
    public Champion findChampionByIdException(){
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser l'id d'un champion.");
    }

}
