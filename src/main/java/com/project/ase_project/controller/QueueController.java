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
    @Operation(summary = "Get the list of all queues", description = "Get the list of all current existing queues.")
    public List<LOLQueue> findAllQueues(){
        return service.getQueues();
    }
    @GetMapping("/queues/{id}")
    @Tag(name = "Queues")
    @Operation(summary = "Get a map's info", description = "Get a map's info given by queueId: queueId, associated map " +
            "and description.")
    public LOLQueue findQueueById(@PathVariable Integer id){
        return service.getQueueById(id);
    }
}
