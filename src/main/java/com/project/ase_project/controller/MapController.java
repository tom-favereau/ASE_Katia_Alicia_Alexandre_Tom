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
    @Operation(summary = "Get the list of all maps", description = "Get the list of all current existing maps.")
    public List<LOLMap> findAllMaps(){
        return service.getMaps();
    }
    @GetMapping("/maps/{id}")
    @Tag(name = "Maps")
    @Operation(summary = "Get a map's info", description = "Get a map's info given by mapId: mapId, name and associated " +
            "notes.")
    public LOLMap findMapById(@PathVariable Integer id){
        return service.getMapById(id);
    }
}
