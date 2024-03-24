package com.project.ase_project.unit.service;

import com.project.ase_project.model.clean.league.League;
import com.project.ase_project.model.clean.summary.Summary;
import com.project.ase_project.model.clean.summoner.Summoner;
import com.project.ase_project.model.dto.league.LeagueDto;
import com.project.ase_project.model.dto.summoner.SummonerDto;
import com.project.ase_project.repository.GradeRepository;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RiotApiServiceTests {
    @InjectMocks
    RiotApiService riotApiService;
    @Mock
    MatchRepository matchRepository;
    @Mock
    GradeRepository gradeRepository;
    @Mock
    RestTemplate restTemplate;
    @Value("${riot.api.key}")
    String apiKey;
    Summoner summoner = new Summoner(
            "Belugafurtif",
            6298,
            156,
            0,
            0,
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
    ArrayList<League> leagues = new ArrayList<League>(List.of(league1, league2));
    Summary summary = new Summary(summoner, leagues);

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

    @Test
    public void getSummaryTest() {
        String summonerName = "Belugafurtif";
        System.out.println(apiKey);

        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + apiKey;
        SummonerDto summonerDto = new SummonerDto(
                "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ",
                "050p5UXp7G8jfRX3IqcPDff_vaWXu-szGXOjFIdsP5UmCpXmvOh9RB8a",
                "krLjP_t5lT5gcJqVMXPP7QWzTnUJa2gvskYSngZz8Oyr8o4-6_T-4-gjgteZLe8Dhz3md-OAcYVUdw",
                "Belugafurtif",
                6298,
                1710279108000L,
                156
        );
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);

        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;
        LeagueDto leagueDto1 = new LeagueDto(
                "13d1771a-99a8-4d56-ad04-5ced66978f76",
                "RANKED_FLEX_SR",
                "DIAMOND",
                "I",
                "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ",
                "Belugafurtif",
                10,
                50,
                50,
                false,
                false,
                false,
                false
        );
        LeagueDto leagueDto2 = new LeagueDto(
                "d597c614-efac-4470-a360-e798620fe4e3",
                "RANKED_SOLO_5x5",
                "GOLD",
                "III",
                "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ",
                "Belugafurtif",
                58,
                126,
                78,
                false,
                false,
                false,
                false
        );
        LeagueDto[] leagueDtos = new LeagueDto[]{leagueDto1, leagueDto2};
        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenReturn(leagueDtos);
        Summary summaryTest = riotApiService.getSummary(summonerName);

        assertEquals(summaryTest.getSummonerId(), summary.getSummonerId());
        assertEquals(summaryTest.getSummonerName(), summary.getSummonerName());
        assertEquals(summaryTest.getSummonerLevel(), summary.getSummonerLevel());
        assertEquals(summaryTest.getProfileIconId(), summary.getProfileIconId());
        assertEquals(summaryTest.getRankFlex(), summary.getRankFlex());
        assertEquals(summaryTest.getRankSolo(), summary.getRankSolo());
        assertEquals(summaryTest.getRegion(), summary.getRegion());
        assertEquals(summaryTest.getAverage(), summary.getAverage());
        assertEquals(summaryTest.getCardinal(), summary.getCardinal());
    }
}