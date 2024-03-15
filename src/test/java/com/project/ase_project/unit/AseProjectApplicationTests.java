package com.project.ase_project.unit;

import com.project.ase_project.AseProjectApplication;
import com.project.ase_project.model.clean.league.League;
import com.project.ase_project.model.clean.summary.Summary;
import com.project.ase_project.model.clean.summoner.Summoner;
import com.project.ase_project.service.RiotApiService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AseProjectApplication.class)
class AseProjectApplicationTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    RiotApiService riotApiService;

    Summoner summoner = new Summoner(
            "Belugafurtif",
            456,
            123,
            "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ"
    );
    League league1 = new League(
            "13d1771a-99a8-4d56-ad04-5ced66978f76",
            "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ",
            "Belugafurtif",
            "RANKED_FLEX_SR",
            "DIAMOND",
            "I",
            10,
            50,
            50
    );
    League league2 = new League(
            "d597c614-efac-4470-a360-e798620fe4e3",
            "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ",
            "Belugafurtif",
            "RANKED_SOLO_5x5",
            "GOLD",
            "III",
            58,
            126,
            78
    );
    ArrayList<League> leagues = new ArrayList<>(List.of(league1, league2));

    Summary summary = new Summary(summoner, leagues);
    @Test
    public void testGetSummonerData() throws Exception {
        // ResponseEntity<Summary> response = new ResponseEntity<>(summary, HttpStatus.OK);

        Mockito.when(riotApiService.getSummary("Belugafurtif")).thenReturn(summary);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/summary/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summonerId").value("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ"))
                .andExpect(jsonPath("$.summonerName").value("Belugafurtif"))
                .andExpect(jsonPath("$.summonerLevel").value(123))
                .andExpect(jsonPath("$.profileIconId").value(456))
                .andExpect(jsonPath("$.rankFlex").value("DIAMOND I 10 LP 50W / 50L"))
                .andExpect(jsonPath("$.rankSolo").value("GOLD III 58 LP 126W / 78L"))
                .andExpect(jsonPath("$.region").value("EUW1"))
                .andExpect(jsonPath("$.grade").value(0));
    }
}
