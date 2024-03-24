package com.project.ase_project.controller;

import com.project.ase_project.model.ddragon.queue.LOLQueue;
import com.project.ase_project.service.QueueService;
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
    public List<LOLQueue> findAllQueues(){
        return service.getQueues();
    }
    @GetMapping("/queues/{id}")
    public LOLQueue findQueueById(@PathVariable Integer id){
        return service.getQueueById(id);
    }
}
