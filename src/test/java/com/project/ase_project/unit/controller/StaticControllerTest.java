package com.project.ase_project.unit.controller;

import com.project.ase_project.AseProjectApplication;
import com.project.ase_project.controller.ChampionController;
import com.project.ase_project.controller.MapController;
import com.project.ase_project.controller.QueueController;
import com.project.ase_project.model.ddragon.champion.Champion;
import com.project.ase_project.model.ddragon.champion.Image;
import com.project.ase_project.model.ddragon.maps.LOLMap;
import com.project.ase_project.model.ddragon.queue.LOLQueue;
import com.project.ase_project.repository.QueueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AseProjectApplication.class)
@AutoConfigureMockMvc
public class StaticControllerTest {

    @Autowired
    public ChampionController championController;
    @Autowired
    public QueueController queueController;
    @Autowired
    public MapController mapController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findAllChampionsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/riot/champions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(167));
    }

    @Test
    public void findChampionByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/riot/champions/254"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value("Vi"))
                .andExpect(jsonPath("$.key").value(254))
                .andExpect(jsonPath("$.name").value("Vi"))
                .andExpect(jsonPath("$.image.full").value("Vi.png"))
                .andExpect(jsonPath("$.image.sprite").value("champion4.png"))
                .andExpect(jsonPath("$.image.group").value("champion"))
                .andExpect(jsonPath("$.image.x").value(336))
                .andExpect(jsonPath("$.image.y").value(96))
                .andExpect(jsonPath("$.image.w").value(48))
                .andExpect(jsonPath("$.image.h").value(48));
    }

    @Test
    public void findAllQueuesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/riot/queues"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(87));
    }

    @Test
    public void findQueueByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/riot/queues/800"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.queueId").value(800))
                .andExpect(jsonPath("$.map").value("Twisted Treeline"))
                .andExpect(jsonPath("$.description").value("Co-op vs. AI Intermediate Bot games"));
    }

    @Test
    public void findAllMapsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/riot/maps"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(16));
    }

    @Test
    public void findMapByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/riot/maps/20"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.mapId").value(20))
                .andExpect(jsonPath("$.mapName").value("Crash Site"))
                .andExpect(jsonPath("$.notes").value("Odyssey: Extraction map"));
    }
}
