package com.project.ase_project.controller;

import com.project.ase_project.model.ddragon.maps.LOLMap;
import com.project.ase_project.service.MapService;
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
public class MapController {
    @Autowired
    private MapService service;
    @GetMapping("/maps")
    @Tag(name = "Maps", description = "Methods for Map APIs")
    @Operation(summary = "Get the list of all maps", description = "Get the list of all current" +
            " maps.")
    public List<LOLMap> findAllMaps(){
        return service.getMaps();
    }
    @GetMapping("/maps/{id}")
    @Tag(name = "Maps")
    @Operation(summary = "Get a map's info", description = "Get a map's info via its id: " +
            "id, name, and associated notes.")
    public LOLMap findMapById(@PathVariable Integer id){
        LOLMap res = service.getMapById(id);
        if (res == null){
            throw new IllegalArgumentException("Erreur 400 : l'id n'est pas correct.");
        } else {
            return res;
        }
    }

    @GetMapping("/maps/")
    @Operation(hidden=true)
    public LOLMap findMapByIdException(){
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser l'id d'une map.");
    }
}
