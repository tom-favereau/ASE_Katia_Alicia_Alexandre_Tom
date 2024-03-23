package com.project.ase_project.unit.service;

import com.project.ase_project.model.clean.summoner.Summoner;
import com.project.ase_project.model.dto.summoner.SummonerDto;
import com.project.ase_project.repository.MatchRepository;
import com.project.ase_project.service.RiotApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RiotApiServiceTests {
    @InjectMocks
    RiotApiService riotApiService;
    @Mock
    MatchRepository matchRepository;
    @Mock
    RestTemplate restTemplate;
    @Value("${riot.api.key}")
    String apiKey;
    Summoner summoner = new Summoner(
            "Belugafurtif",
            6298,
            156,
            "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ"
    );

    @Test
    public void getSummonerByNameTest() {
        String summonerName = "Belugafurtif";
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + apiKey;
        SummonerDto summonerDto = new SummonerDto(
                "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ",
                "050p5UXp7G8jfRX3IqcPDff_vaWXu-szGXOjFIdsP5UmCpXmvOh9RB8a",
                "krLjP_t5lT5gcJqVMXPP7QWzTnUJa2gvskYSngZz8Oyr8o4-6_T-4-gjgteZLe8Dhz3md-OAcYVUdw",
                "Belugafurtif",
                6298,
                1710279108000L,
                156
        );

        Mockito.when(restTemplate.getForObject(apiUrl, SummonerDto.class)).thenReturn(summonerDto);
        Summoner summonerTest = riotApiService.getSummonerByName(summonerName);

        assertEquals(summonerTest.getName(), summoner.getName());
        assertEquals(summonerTest.getProfileIconId(), summoner.getProfileIconId());
        assertEquals(summonerTest.getSummonerLevel(), summoner.getSummonerLevel());
        assertEquals(summonerTest.getId(), summoner.getId());
    }
}