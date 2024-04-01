package com.project.ase_project.unit.service;

import com.project.ase_project.repository.ChampionRepository;
import com.project.ase_project.repository.MapRepository;
import com.project.ase_project.repository.QueueRepository;
import com.project.ase_project.service.RiotApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource(locations = "/com/project/ase_project/unit/service/test.properties")
@ComponentScan(basePackages = {"com.project.ase_project.service", "com.project.ase_project.controller"})
public class StaticDataTests {

    @Autowired
    private ChampionRepository championRepository;
    @Autowired
    private MapRepository mapRepository;
    @Autowired
    private QueueRepository queueRepository;
    @Autowired
    private RiotApiService riotApiService;



    @BeforeEach
    public void setUp() {
        // Clear any existing data in the repository before each test
        championRepository.deleteAll();
        mapRepository.deleteAll();
        queueRepository.deleteAll();

    }

    @Test
    public void testChampionRepositoryInitialization() throws IOException, URISyntaxException {
        // Assert that repository initialization logic has been executed
        assertFalse(championRepository.count() > 0);
        assertTrue(riotApiService.initializeChampions());
        assertTrue(championRepository.count() > 0);
        assertFalse(riotApiService.initializeChampions());
    }

    @Test
    public void testMapRepositoryInitialization() throws IOException, URISyntaxException {
        // Assert that repository initialization logic has been executed*
        assertFalse(mapRepository.count() > 0);
        assertTrue(riotApiService.initializeMaps());
        assertTrue(mapRepository.count() > 0);
        assertFalse(riotApiService.initializeMaps());
    }

    @Test
    public void testQueueRepositoryInitialization() throws IOException, URISyntaxException {
        // Assert that repository initialization logic has been executed
        assertFalse(queueRepository.count() > 0);
        assertTrue(riotApiService.initializeQueues());
        assertTrue(queueRepository.count() > 0);
        assertFalse(riotApiService.initializeQueues());
    }
}
