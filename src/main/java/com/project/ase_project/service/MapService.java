package com.project.ase_project.service;

import com.project.ase_project.model.champion.Champion;
import com.project.ase_project.model.maps.LOLMap;
import com.project.ase_project.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService {
    @Autowired
    private MapRepository repository;

    public List<LOLMap> getMaps(){
        return repository.findAll();
    }

    public LOLMap getMapById(Integer id){
        return repository.findById(id).orElse(null);
    }
}
