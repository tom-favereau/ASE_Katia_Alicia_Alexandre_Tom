package com.project.ase_project.controller;

import com.project.ase_project.model.ddragon.queue.LOLQueue;
import com.project.ase_project.service.QueueService;
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
public class QueueController {
    @Autowired
    private QueueService service;
    @GetMapping("/queues")
    @Tag(name = "Queues", description = "Methods for Queue APIs")
    @Operation(summary = "Get the list of all queues", description = "Get the list of all current" +
            " queues.")
    public List<LOLQueue> findAllQueues(){
        return service.getQueues();
    }
    @GetMapping("/queues/{id}")
    @Tag(name = "Queues")
    @Operation(summary = "Get a queue's info", description = "Get a queue's info via its id: " +
            "id, associated map and description.")
    public LOLQueue findQueueById(@PathVariable Integer id){
        LOLQueue res = service.getQueueById(id);
        if (res == null){
            throw new IllegalArgumentException("Erreur 400 : l'id n'est pas correct.");
        } else {
            return res;
        }
    }

    @GetMapping("/queues/")
    @Operation(hidden=true)
    public LOLQueue findQueueByIdException(){
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser l'id d'une queue.");
    }
}
