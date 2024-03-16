package com.project.ase_project.service;

import com.project.ase_project.model.queue.LOLQueue;
import com.project.ase_project.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;

@Service
public class QueueService {
    @Autowired
    private QueueRepository repository;

    public List<LOLQueue> getQueues(){
        return repository.findAll();
    }

    public LOLQueue getQueueById(Integer id){
        return repository.findById(id).orElse(null);
    }
}
