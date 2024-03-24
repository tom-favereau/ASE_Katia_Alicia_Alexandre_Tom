package com.project.ase_project.unit.service;

import com.project.ase_project.model.clean.summoner.Summoner;
import com.project.ase_project.model.dto.summoner.SummonerDto;
import com.project.ase_project.repository.MatchRepository;
import com.project.ase_project.service.RiotApiService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RiotApiServiceTests {
    @InjectMocks
    RiotApiService riotApiService;
    @Mock
    MatchRepository matchRepository;
    @Mock
    RestTemplate restTemplate;
    @Value("${riot.api.key}")
    String apiKey;

    @Mock
    SummonerDto summonerDto = new SummonerDto(
            "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ",
            "050p5UXp7G8jfRX3IqcPDff_vaWXu-szGXOjFIdsP5UmCpXmvOh9RB8a",
            "krLjP_t5lT5gcJqVMXPP7QWzTnUJa2gvskYSngZz8Oyr8o4-6_T-4-gjgteZLe8Dhz3md-OAcYVUdw",
            "Belugafurtif",
            6298,
            1710279108000L,
            156
    );
    Summoner summoner = new Summoner(
            "Belugafurtif",
            6298,
            156,
            "krLjP_t5lT5gcJqVMXPP7QWzTnUJa2gvskYSngZz8Oyr8o4-6_T-4-gjgteZLe8Dhz3md-OAcYVUdw",
            "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ"
    );

    @Test
    public void getSummonerByNameTest() {
        String summonerName = "Belugafurtif";
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl, SummonerDto.class)).thenReturn(summonerDto);
        Mockito.when(SummonerDto.toSummoner(summonerDto)).thenReturn(summoner);

        Mockito.when(summonerDto.getName()).thenReturn("SummonerName");
        Mockito.when(summonerDto.getProfileIconId()).thenReturn(6298);
        Mockito.when(summonerDto.getSummonerLevel()).thenReturn(156L);
        Mockito.when(summonerDto.getId()).thenReturn("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ");

        Summoner summoner = riotApiService.getSummonerByName("Belugafurtif");

        assertEquals(summoner.getName(), summonerDto.getName());
        assertEquals(summoner.getProfileIconId(), summonerDto.getProfileIconId());
        assertEquals(summoner.getSummonerLevel(), summonerDto.getSummonerLevel());
        assertEquals(summoner.getId(), summonerDto.getId());
    }
}
