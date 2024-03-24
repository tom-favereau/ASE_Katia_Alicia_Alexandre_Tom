package com.project.ase_project.controller;

import com.project.ase_project.model.ddragon.maps.LOLMap;
import com.project.ase_project.service.MapService;
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
    public List<LOLMap> findAllMaps(){
        return service.getMaps();
    }
    @GetMapping("/maps/{id}")
    public LOLMap findMapById(@PathVariable Integer id){
        return service.getMapById(id);
    }
}
