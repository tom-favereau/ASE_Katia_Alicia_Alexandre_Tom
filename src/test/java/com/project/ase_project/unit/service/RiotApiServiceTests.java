package com.project.ase_project.unit.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ase_project.exception.*;
import com.project.ase_project.model.clean.MostPlayedChampions.ChampionsPlayed;
import com.project.ase_project.model.clean.MostPlayedGameModes.GameModesPlayed;
import com.project.ase_project.model.clean.grade.Grade;
import com.project.ase_project.model.clean.league.League;
import com.project.ase_project.model.clean.match.Match;
import com.project.ase_project.model.clean.summary.Summary;
import com.project.ase_project.model.clean.summoner.Summoner;
import com.project.ase_project.model.ddragon.queue.LOLQueue;
import com.project.ase_project.model.dto.league.LeagueDto;
import com.project.ase_project.model.dto.match.MatchDto;
import com.project.ase_project.model.dto.summoner.SummonerDto;
import com.project.ase_project.repository.GradeRepository;
import com.project.ase_project.repository.MatchRepository;
import com.project.ase_project.repository.QueueRepository;
import com.project.ase_project.service.RiotApiService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RiotApiServiceTests {
    @InjectMocks
    RiotApiService riotApiService;
    @Mock
    MatchRepository matchRepository;
    @Mock
    GradeRepository gradeRepository;
    @Mock
    QueueRepository queueRepository;
    @Mock
    RestTemplate restTemplate;
    @Value("${riot.api.key}")
    String apiKey;
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
            0,
            0,
            "krLjP_t5lT5gcJqVMXPP7QWzTnUJa2gvskYSngZz8Oyr8o4-6_T-4-gjgteZLe8Dhz3md-OAcYVUdw",
            "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ"
    );
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
    static MatchDto matchDto;
    static Match match;
    Summary summary = new Summary(summoner, leagues);
    Grade grade = new Grade(
            "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ",
            "Belugafurtif",
            3.8f,
            5
    );
    static String[] matchList;
    static MatchDto matchDto1;
    static MatchDto matchDto2;
    static MatchDto matchDto3;
    static MatchDto matchDto4;
    static MatchDto matchDto5;
    static Match match1;
    static Match match2;
    static Match match3;
    static Match match4;
    static Match match5;
    ChampionsPlayed championsPlayed = new ChampionsPlayed(
            "aSaFsPEmtoVFz6SQzGIjsGoZHn4Otw67YxDWTE8bA84W-Wgl",
            "Belugafurtif",

            5,
            2,
            3,
            0.4f,

            26,
            30,
            25,
            1.7f,

            19,
            "Warwick",

            3,
            2,
            1,
            0.67f,

            21,
            21,
            24,
            2.14f,

            23,
            "Tryndamere",

            1,
            0,
            1,
            0.0f,

            5,
            9,
            1,
            0.67f,

            19,
            "Warwick",

            3,
            2,
            1,
            0.67f,

            21,
            21,
            24,
            2.14f,

            23,
            "Tryndamere",

            1,
            0,
            1,
            0.0f,

            5,
            9,
            1,
            0.67f
    );

    GameModesPlayed gameModesPlayed = new GameModesPlayed(
            "aSaFsPEmtoVFz6SQzGIjsGoZHn4Otw67YxDWTE8bA84W-Wgl",
            "Belugafurtif",

            5,
            2,
            3,
            0.4f,

            26,
            30,
            25,
            1.7f,

            420,
            "5v5 Ranked Solo games",

            4,
            2,
            2,
            0.5f,

            21,
            21,
            24,
            2.14f,

            400,
            "5v5 Draft Pick games",

            1,
            0,
            1,
            0.0f,

            5,
            9,
            1,
            0.67f,

            420,
            "5v5 Ranked Solo games",

            4,
            2,
            2,
            0.5f,

            21,
            21,
            24,
            2.14f,

            400,
            "5v5 Draft Pick games",

            1,
            0,
            1,
            0.0f,

            5,
            9,
            1,
            0.67f
    );

    @BeforeAll
    public static void matchSetUp() throws IOException, URISyntaxException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new URI("https://europe.api.riotgames.com/lol/match/v5/matches/EUW1_6882272431?api_key=RGAPI-7824e8d4-5ed0-4244-8b26-67ba3e260cc2").toURL());
        matchDto = objectMapper.readValue(jsonNode.toString(), MatchDto.class);
        match = matchDto.toMatch();

        // Condensé
        JsonNode jsonNode1 = objectMapper.readTree(new URI("https://europe.api.riotgames.com/lol/match/v5/matches/EUW1_6760205418?api_key=RGAPI-7824e8d4-5ed0-4244-8b26-67ba3e260cc2").toURL());
        matchDto1 = objectMapper.readValue(jsonNode1.toString(), MatchDto.class);
        match1 = matchDto1.toMatch();

        JsonNode jsonNode2 = objectMapper.readTree(new URI("https://europe.api.riotgames.com/lol/match/v5/matches/EUW1_6882261316?api_key=RGAPI-7824e8d4-5ed0-4244-8b26-67ba3e260cc2").toURL());
        matchDto2 = objectMapper.readValue(jsonNode2.toString(), MatchDto.class);
        match2 = matchDto2.toMatch();

        JsonNode jsonNode3 = objectMapper.readTree(new URI("https://europe.api.riotgames.com/lol/match/v5/matches/EUW1_6882191359?api_key=RGAPI-7824e8d4-5ed0-4244-8b26-67ba3e260cc2").toURL());
        matchDto3 = objectMapper.readValue(jsonNode3.toString(), MatchDto.class);
        match3 = matchDto3.toMatch();

        JsonNode jsonNode4 = objectMapper.readTree(new URI("https://europe.api.riotgames.com/lol/match/v5/matches/EUW1_6882183404?api_key=RGAPI-7824e8d4-5ed0-4244-8b26-67ba3e260cc2").toURL());
        matchDto4 = objectMapper.readValue(jsonNode4.toString(), MatchDto.class);
        match4 = matchDto4.toMatch();

        JsonNode jsonNode5 = objectMapper.readTree(new URI("https://europe.api.riotgames.com/lol/match/v5/matches/EUW1_6882143453?api_key=RGAPI-7824e8d4-5ed0-4244-8b26-67ba3e260cc2").toURL());
        matchDto5 = objectMapper.readValue(jsonNode5.toString(), MatchDto.class);
        match5 = matchDto5.toMatch();

        matchList = new String[]{match1.getMatchId(), match2.getMatchId(), match3.getMatchId(), match4.getMatchId(), match5.getMatchId()};
    }

    @Test
    public void getSummonerByNameNoGradeTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, SummonerDto.class)).thenReturn(summonerDto);
        Summoner summonerTest = riotApiService.getSummonerByName(summonerDto.getName());

        assertEquals(summonerTest.getName(), summoner.getName());
        assertEquals(summonerTest.getProfileIconId(), summoner.getProfileIconId());
        assertEquals(summonerTest.getSummonerLevel(), summoner.getSummonerLevel());
        assertEquals(summonerTest.getAverage(), summoner.getAverage());
        assertEquals(summonerTest.getCardinal(), summoner.getCardinal());
        assertEquals(summonerTest.getPuuid(), summoner.getPuuid());
        assertEquals(summonerTest.getId(), summoner.getId());
    }

    @Test
    public void getSummonerByNameWithGradeTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, SummonerDto.class)).thenReturn(summonerDto);
        Mockito.when(gradeRepository.findById(summoner.getId())).thenReturn(Optional.ofNullable(grade));

        Summoner summonerTest = riotApiService.getSummonerByName(summonerDto.getName());

        assertEquals(summonerTest.getName(), summoner.getName());
        assertEquals(summonerTest.getProfileIconId(), summoner.getProfileIconId());
        assertEquals(summonerTest.getSummonerLevel(), summoner.getSummonerLevel());
        assertEquals(summonerTest.getAverage(), grade.getAverage());
        assertEquals(summonerTest.getCardinal(), grade.getCardinal());
        assertEquals(summonerTest.getPuuid(), summoner.getPuuid());
        assertEquals(summonerTest.getId(), summoner.getId());
    }

    @Test
    public void getSummonerByNameSummonerNullTest()  {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + "Belugafurti" + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, SummonerDto.class)).thenReturn(null);

        assertThrows(SummonerNotFoundException.class, () -> riotApiService.getSummonerByName("Belugafurti"));
    }

    @Test
    public void getSummonerByNameBadRequestTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + "Belugafurtif" + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, SummonerDto.class)).thenThrow(HttpClientErrorException.BadRequest.class);

        assertThrows(BadRequestException.class, () -> riotApiService.getSummonerByName("Belugafurtif"));
    }

    @Test
    public void getSummonerByNameUnauthorizedTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + "Belugafurtif" + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, SummonerDto.class)).thenThrow(HttpClientErrorException.Unauthorized.class);

        assertThrows(BadRequestException.class, () -> riotApiService.getSummonerByName("Belugafurtif"));
    }

    @Test
    public void getSummonerByNameForbiddenTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + "Belugafurtif" + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, SummonerDto.class)).thenThrow(HttpClientErrorException.Forbidden.class);

        assertThrows(BadRequestException.class, () -> riotApiService.getSummonerByName("Belugafurtif"));
    }

    @Test
    public void getSummonerByNameNotFoundTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + "Belugafurtif" + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, SummonerDto.class)).thenThrow(HttpClientErrorException.NotFound.class);

        assertThrows(SummonerNotFoundException.class, () -> riotApiService.getSummonerByName("Belugafurtif"));
    }

    @Test
    public void getSummonerByNameMethodNotAllowedTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + "Belugafurtif" + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, SummonerDto.class)).thenThrow(HttpClientErrorException.MethodNotAllowed.class);

        assertThrows(MethodNotAllowed.class, () -> riotApiService.getSummonerByName("Belugafurtif"));
    }

    @Test
    public void getSummonerByNameUnsupportedMediaTypeTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + "Belugafurtif" + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, SummonerDto.class)).thenThrow(HttpClientErrorException.UnsupportedMediaType.class);

        assertThrows(UnsupportedMediaType.class, () -> riotApiService.getSummonerByName("Belugafurtif"));
    }

    @Test
    public void getSummonerByNameInternalServerErrorTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + "Belugafurtif" + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, SummonerDto.class)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(InternalServerError.class, () -> riotApiService.getSummonerByName("Belugafurtif"));
    }

    @Test
    public void getSummonerByNameBadGatewayTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + "Belugafurtif" + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, SummonerDto.class)).thenThrow(HttpServerErrorException.BadGateway.class);

        assertThrows(BadGateway.class, () -> riotApiService.getSummonerByName("Belugafurtif"));
    }

    @Test
    public void getSummonerByNameServiceUnavailableTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + "Belugafurtif" + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, SummonerDto.class)).thenThrow(HttpServerErrorException.ServiceUnavailable.class);

        assertThrows(ServiceUnavailable.class, () -> riotApiService.getSummonerByName("Belugafurtif"));
    }

    @Test
    public void getSummonerByNameGatewayTimeoutTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + "Belugafurtif" + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, SummonerDto.class)).thenThrow(HttpServerErrorException.GatewayTimeout.class);

        assertThrows(GatewayTimeout.class, () -> riotApiService.getSummonerByName("Belugafurtif"));
    }

    @Test
    public void getMatchByIdTest() {
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match.getMatchId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, MatchDto.class)).thenReturn(matchDto);
        Match matchTest = riotApiService.getMatchById(match.getMatchId());

        assertEquals(matchTest.getMatchId(), match.getMatchId());
        assertEquals(matchTest.getMetadata(), match.getMetadata());
        assertEquals(matchTest.getParticipants(), match.getParticipants());
        assertEquals(matchTest.getTeams(), match.getTeams());
        assertEquals(matchTest.getDrafts(), match.getDrafts());

        ArgumentCaptor<Match> matchCaptor = ArgumentCaptor.forClass(Match.class);
        Mockito.verify(matchRepository).save(matchCaptor.capture());
        Match matchSaved = matchCaptor.getValue();

        assertEquals(matchSaved.getMatchId(), match.getMatchId());
        assertEquals(matchSaved.getMetadata(), match.getMetadata());
        assertEquals(matchSaved.getParticipants(), match.getParticipants());
        assertEquals(matchSaved.getTeams(), match.getTeams());
        assertEquals(matchSaved.getDrafts(), match.getDrafts());
    }

    @Test
    public void getMatchByIdMatchNullTest()  {
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match.getMatchId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, MatchDto.class)).thenReturn(null);

        assertThrows(MatchNotFoundException.class, () -> riotApiService.getMatchById(match.getMatchId()));
    }

    @Test
    public void getMatchByIdBadRequestTest() {
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match.getMatchId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, MatchDto.class)).thenThrow(HttpClientErrorException.BadRequest.class);

        assertThrows(BadRequestException.class, () -> riotApiService.getMatchById(match.getMatchId()));
    }

    @Test
    public void getMatchByIdUnauthorizedTest() {
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match.getMatchId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, MatchDto.class)).thenThrow(HttpClientErrorException.Unauthorized.class);

        assertThrows(BadRequestException.class, () -> riotApiService.getMatchById(match.getMatchId()));
    }

    @Test
    public void getMatchByIdForbiddenTest() {
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match.getMatchId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, MatchDto.class)).thenThrow(HttpClientErrorException.Forbidden.class);

        assertThrows(BadRequestException.class, () -> riotApiService.getMatchById(match.getMatchId()));
    }

    @Test
    public void getMatchByIdMethodNotAllowedTest() {
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match.getMatchId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, MatchDto.class)).thenThrow(HttpClientErrorException.MethodNotAllowed.class);

        assertThrows(MethodNotAllowed.class, () -> riotApiService.getMatchById(match.getMatchId()));
    }

    @Test
    public void getMatchByIdUnsupportedMediaTypeTest() {
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match.getMatchId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, MatchDto.class)).thenThrow(HttpClientErrorException.UnsupportedMediaType.class);

        assertThrows(UnsupportedMediaType.class, () -> riotApiService.getMatchById(match.getMatchId()));
    }

    @Test
    public void getMatchByIdInternalServerErrorTest() {
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match.getMatchId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, MatchDto.class)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(InternalServerError.class, () -> riotApiService.getMatchById(match.getMatchId()));
    }

    @Test
    public void getMatchByIdBadGatewayTest() {
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match.getMatchId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, MatchDto.class)).thenThrow(HttpServerErrorException.BadGateway.class);

        assertThrows(BadGateway.class, () -> riotApiService.getMatchById(match.getMatchId()));
    }

    @Test
    public void getMatchByIdServiceUnavailableTest() {
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match.getMatchId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, MatchDto.class)).thenThrow(HttpServerErrorException.ServiceUnavailable.class);

        assertThrows(ServiceUnavailable.class, () -> riotApiService.getMatchById(match.getMatchId()));
    }

    @Test
    public void getMatchByIdGatewayTimeoutTest() {
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match.getMatchId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, MatchDto.class)).thenThrow(HttpServerErrorException.GatewayTimeout.class);

        assertThrows(GatewayTimeout.class, () -> riotApiService.getMatchById(match.getMatchId()));
    }

    @Test
    public void getRankByIdTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key="+apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenReturn(leagueDtos);
        ArrayList<League> leaguesTest = riotApiService.getRankById(summonerDto.getId());

        assertEquals(leaguesTest.get(0).getLeagueId(), leagues.get(0).getLeagueId());
        assertEquals(leaguesTest.get(0).getSummonerId(), leagues.get(0).getSummonerId());
        assertEquals(leaguesTest.get(0).getSummonerName(), leagues.get(0).getSummonerName());
        assertEquals(leaguesTest.get(0).getQueueType(), leagues.get(0).getQueueType());
        assertEquals(leaguesTest.get(0).getTier(), leagues.get(0).getTier());
        assertEquals(leaguesTest.get(0).getRank(), leagues.get(0).getRank());
        assertEquals(leaguesTest.get(0).getLeaguePoints(), leagues.get(0).getLeaguePoints());
        assertEquals(leaguesTest.get(0).getWins(), leagues.get(0).getWins());
        assertEquals(leaguesTest.get(0).getLosses(), leagues.get(0).getLosses());

        assertEquals(leaguesTest.get(1).getLeagueId(), leagues.get(1).getLeagueId());
        assertEquals(leaguesTest.get(1).getSummonerId(), leagues.get(1).getSummonerId());
        assertEquals(leaguesTest.get(1).getSummonerName(), leagues.get(1).getSummonerName());
        assertEquals(leaguesTest.get(1).getQueueType(), leagues.get(1).getQueueType());
        assertEquals(leaguesTest.get(1).getTier(), leagues.get(1).getTier());
        assertEquals(leaguesTest.get(1).getRank(), leagues.get(1).getRank());
        assertEquals(leaguesTest.get(1).getLeaguePoints(), leagues.get(1).getLeaguePoints());
        assertEquals(leaguesTest.get(1).getWins(), leagues.get(1).getWins());
        assertEquals(leaguesTest.get(1).getLosses(), leagues.get(1).getLosses());
    }

    @Test
    public void getRankByIdPartialLeaguesTest1() {
        LeagueDto[] leagueDto1 = new LeagueDto[]{this.leagueDto1};
        ArrayList<League> leagues1 = new ArrayList<>(List.of(league1));
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenReturn(leagueDto1);
        ArrayList<League> leaguesTest = riotApiService.getRankById(summonerDto.getId());

        assertEquals(leaguesTest.get(0).getLeagueId(), leagues1.get(0).getLeagueId());
        assertEquals(leaguesTest.get(0).getSummonerId(), leagues1.get(0).getSummonerId());
        assertEquals(leaguesTest.get(0).getSummonerName(), leagues1.get(0).getSummonerName());
        assertEquals(leaguesTest.get(0).getQueueType(), leagues1.get(0).getQueueType());
        assertEquals(leaguesTest.get(0).getTier(), leagues1.get(0).getTier());
        assertEquals(leaguesTest.get(0).getRank(), leagues1.get(0).getRank());
        assertEquals(leaguesTest.get(0).getLeaguePoints(), leagues1.get(0).getLeaguePoints());
        assertEquals(leaguesTest.get(0).getWins(), leagues1.get(0).getWins());
        assertEquals(leaguesTest.get(0).getLosses(), leagues1.get(0).getLosses());

        assertEquals(leaguesTest.get(1).getLeagueId(), "");
        assertEquals(leaguesTest.get(1).getSummonerId(), leagues1.get(0).getSummonerId());
        assertEquals(leaguesTest.get(1).getSummonerName(), "");
        assertEquals(leaguesTest.get(1).getQueueType(), "RANKED_SOLO_5x5");
        assertEquals(leaguesTest.get(1).getTier(), "UNRANKED");
        assertEquals(leaguesTest.get(1).getRank(), "");
        assertEquals(leaguesTest.get(1).getLeaguePoints(), 0);
        assertEquals(leaguesTest.get(1).getWins(), 0);
        assertEquals(leaguesTest.get(1).getLosses(), 0);
    }

    @Test
    public void getRankByIdPartialLeaguesTest2() {
        LeagueDto[] leagueDto2 = new LeagueDto[]{this.leagueDto2};
        ArrayList<League> leagues2 = new ArrayList<>(List.of(league2));
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenReturn(leagueDto2);
        ArrayList<League> leaguesTest = riotApiService.getRankById(summonerDto.getId());

        assertEquals(leaguesTest.get(0).getLeagueId(), leagues2.get(0).getLeagueId());
        assertEquals(leaguesTest.get(0).getSummonerId(), leagues2.get(0).getSummonerId());
        assertEquals(leaguesTest.get(0).getSummonerName(), leagues2.get(0).getSummonerName());
        assertEquals(leaguesTest.get(0).getQueueType(), leagues2.get(0).getQueueType());
        assertEquals(leaguesTest.get(0).getTier(), leagues2.get(0).getTier());
        assertEquals(leaguesTest.get(0).getRank(), leagues2.get(0).getRank());
        assertEquals(leaguesTest.get(0).getLeaguePoints(), leagues2.get(0).getLeaguePoints());
        assertEquals(leaguesTest.get(0).getWins(), leagues2.get(0).getWins());
        assertEquals(leaguesTest.get(0).getLosses(), leagues2.get(0).getLosses());

        assertEquals(leaguesTest.get(1).getLeagueId(), "");
        assertEquals(leaguesTest.get(1).getSummonerId(), leagues2.get(0).getSummonerId());
        assertEquals(leaguesTest.get(1).getSummonerName(), "");
        assertEquals(leaguesTest.get(1).getQueueType(), "RANKED_FLEX_SR");
        assertEquals(leaguesTest.get(1).getTier(), "UNRANKED");
        assertEquals(leaguesTest.get(1).getRank(), "");
        assertEquals(leaguesTest.get(1).getLeaguePoints(), 0);
        assertEquals(leaguesTest.get(1).getWins(), 0);
        assertEquals(leaguesTest.get(1).getLosses(), 0);
    }

    @Test
    public void getRankByIdEmptyLeagueTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenReturn(new LeagueDto[]{});
        ArrayList<League> leaguesTest = riotApiService.getRankById(summonerDto.getId());

        assertEquals(leaguesTest.size(), 0);
    }

    @Test
    public void getRankByIdMatchNullTest()  {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenReturn(null);

        assertThrows(LeaguesNotFoundException.class, () -> riotApiService.getRankById(summonerDto.getId()));
    }

    @Test
    public void getRankByIdBadRequestTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenThrow(HttpClientErrorException.BadRequest.class);

        assertThrows(BadRequestException.class, () -> riotApiService.getRankById(summonerDto.getId()));
    }

    @Test
    public void getRankByIdUnauthorizedTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenThrow(HttpClientErrorException.Unauthorized.class);

        assertThrows(BadRequestException.class, () -> riotApiService.getRankById(summonerDto.getId()));
    }

    @Test
    public void getRankByIdForbiddenTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenThrow(HttpClientErrorException.Forbidden.class);

        assertThrows(BadRequestException.class, () -> riotApiService.getRankById(summonerDto.getId()));
    }

    @Test
    public void getRankByIdNotFoundTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenThrow(HttpClientErrorException.NotFound.class);

        assertThrows(LeaguesNotFoundException.class, () -> riotApiService.getRankById(summonerDto.getId()));
    }

    @Test
    public void getRankByIdMethodNotAllowedTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenThrow(HttpClientErrorException.MethodNotAllowed.class);

        assertThrows(MethodNotAllowed.class, () -> riotApiService.getRankById(summonerDto.getId()));
    }

    @Test
    public void getRankByIdUnsupportedMediaTypeTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenThrow(HttpClientErrorException.UnsupportedMediaType.class);

        assertThrows(UnsupportedMediaType.class, () -> riotApiService.getRankById(summonerDto.getId()));
    }

    @Test
    public void getRankByIdInternalServerErrorTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(InternalServerError.class, () -> riotApiService.getRankById(summonerDto.getId()));
    }

    @Test
    public void getRankByIdBadGatewayTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenThrow(HttpServerErrorException.BadGateway.class);

        assertThrows(BadGateway.class, () -> riotApiService.getRankById(summonerDto.getId()));
    }

    @Test
    public void getRankByIdServiceUnavailableTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenThrow(HttpServerErrorException.ServiceUnavailable.class);

        assertThrows(ServiceUnavailable.class, () -> riotApiService.getRankById(summonerDto.getId()));
    }

    @Test
    public void getRankByIdGatewayTimeoutTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenThrow(HttpServerErrorException.GatewayTimeout.class);

        assertThrows(GatewayTimeout.class, () -> riotApiService.getRankById(summonerDto.getId()));
    }

    @Test
    public void getSummaryByNameTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);

        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenReturn(leagueDtos);
        Summary summaryTest = riotApiService.getSummaryByName(summonerDto.getName());

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

    @Test
    public void getSummaryByNameBadRequestTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpClientErrorException.BadRequest.class);

        assertThrows(BadRequestException.class, () -> riotApiService.getSummaryByName(summonerDto.getName()));
    }

    @Test
    public void getSummaryByNameSummonerNotFoundTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpClientErrorException.NotFound.class);

        assertThrows(SummonerNotFoundException.class, () -> riotApiService.getSummaryByName(summonerDto.getName()));
    }

    @Test
    public void getSummaryByNameRankNotFoundTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);

        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenThrow(HttpClientErrorException.NotFound.class);

        assertThrows(LeaguesNotFoundException.class, () -> riotApiService.getSummaryByName(summonerDto.getName()));
    }

    @Test
    public void getSummaryByNameMethodNotAllowedTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpClientErrorException.MethodNotAllowed.class);

        assertThrows(MethodNotAllowed.class, () -> riotApiService.getSummaryByName(summonerDto.getName()));
    }

    @Test
    public void getSummaryByNameUnsupportedMediaTypeTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpClientErrorException.UnsupportedMediaType.class);

        assertThrows(UnsupportedMediaType.class, () -> riotApiService.getSummaryByName(summonerDto.getName()));
    }

    @Test
    public void getSummaryByNameInternalServerErrorTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(InternalServerError.class, () -> riotApiService.getSummaryByName(summonerDto.getName()));
    }

    @Test
    public void getSummaryByNameBadGatewayTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpServerErrorException.BadGateway.class);

        assertThrows(BadGateway.class, () -> riotApiService.getSummaryByName(summonerDto.getName()));
    }

    @Test
    public void getSummaryByNameServiceUnavailableTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpServerErrorException.ServiceUnavailable.class);

        assertThrows(ServiceUnavailable.class, () -> riotApiService.getSummaryByName(summonerDto.getName()));
    }

    @Test
    public void getSummaryByNameGatewayTimeoutTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpServerErrorException.GatewayTimeout.class);

        assertThrows(GatewayTimeout.class, () -> riotApiService.getSummaryByName(summonerDto.getName()));
    }

    @Test
    public void postGradeNonEmptyTest() {
        int note = 5;
        Grade oldGrade = new Grade(grade.getId(), grade.getSummonerName(), grade.getAverage(), grade.getCardinal());
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);
        Mockito.when(gradeRepository.findById(summonerDto.getId())).thenReturn(Optional.of(oldGrade));

        riotApiService.postGrade("Belugafurtif", note);

        ArgumentCaptor<Grade> gradeCaptor = ArgumentCaptor.forClass(Grade.class);
        Mockito.verify(gradeRepository).save(gradeCaptor.capture());
        Grade newGrade = gradeCaptor.getValue();

        assertEquals(newGrade.getId(), grade.getId());
        assertEquals(newGrade.getSummonerName(), grade.getSummonerName());
        assertEquals(newGrade.getAverage(), (grade.getAverage()*grade.getCardinal() + note)/(grade.getCardinal() + 1));
        assertEquals(newGrade.getCardinal(), grade.getCardinal() + 1);
    }

    @Test
    public void postGradeEmptyTest() {
        int note = 5;
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);
        Mockito.when(gradeRepository.findById(summonerDto.getId())).thenReturn(Optional.empty());

        riotApiService.postGrade("Belugafurtif", note);

        ArgumentCaptor<Grade> gradeCaptor = ArgumentCaptor.forClass(Grade.class);
        Mockito.verify(gradeRepository).save(gradeCaptor.capture());
        Grade newGrade = gradeCaptor.getValue();

        assertEquals(newGrade.getId(), summonerDto.getId());
        assertEquals(newGrade.getSummonerName(), summonerDto.getName());
        assertEquals(newGrade.getAverage(), note);
        assertEquals(newGrade.getCardinal(), 1);
    }

    @Test
    public void postGradeBadRequestTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpClientErrorException.BadRequest.class);

        assertThrows(BadRequestException.class, () -> riotApiService.postGrade("Belugafurtif", 5));
    }

    @Test
    public void postGradeSummonerNotFoundTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpClientErrorException.NotFound.class);

        assertThrows(SummonerNotFoundException.class, () -> riotApiService.postGrade("Belugafurtif", 5));
    }

    @Test
    public void postGradeMethodNotAllowedTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpClientErrorException.MethodNotAllowed.class);

        assertThrows(MethodNotAllowed.class, () -> riotApiService.postGrade("Belugafurtif", 5));
    }

    @Test
    public void postGradeUnsupportedMediaTypeTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpClientErrorException.UnsupportedMediaType.class);

        assertThrows(UnsupportedMediaType.class, () -> riotApiService.postGrade("Belugafurtif", 5));
    }

    @Test
    public void postGradeInternalServerErrorTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(InternalServerError.class, () -> riotApiService.postGrade("Belugafurtif", 5));
    }

    @Test
    public void postGradeBadGatewayTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpServerErrorException.BadGateway.class);

        assertThrows(BadGateway.class, () -> riotApiService.postGrade("Belugafurtif", 5));
    }

    @Test
    public void postGradeServiceUnavailableTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpServerErrorException.ServiceUnavailable.class);

        assertThrows(ServiceUnavailable.class, () -> riotApiService.postGrade("Belugafurtif", 5));
    }

    @Test
    public void postGradeGatewayTimeoutTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpServerErrorException.GatewayTimeout.class);

        assertThrows(GatewayTimeout.class, () -> riotApiService.postGrade("Belugafurtif", 5));
    }

    @Test
    public void getMatchesTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);

        String apiUrl1 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match1.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl1, MatchDto.class)).thenReturn(matchDto1);

        String apiUrl2 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match2.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl2, MatchDto.class)).thenReturn(matchDto2);

        String apiUrl3 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match3.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl3, MatchDto.class)).thenReturn(matchDto3);

        String apiUrl4 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match4.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl4, MatchDto.class)).thenReturn(matchDto4);

        String apiUrl5 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match5.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl5, MatchDto.class)).thenReturn(matchDto5);

        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summoner.getPuuid()
                + "/ids?startTime="
                + "&endTime="
                + "&queue="
                + "&type="
                + "&start=" + 0
                + "&count=" + 5
                + "&api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl, String[].class)).thenReturn(matchList);

        ArrayList<Match> matchArrayList = riotApiService.getMatches(summoner.getName(), null, null, null, null, 0, 5);

        assertEquals(matchArrayList.get(0).getMatchId(), match1.getMatchId());
        assertEquals(matchArrayList.get(1).getMatchId(), match2.getMatchId());
        assertEquals(matchArrayList.get(2).getMatchId(), match3.getMatchId());
        assertEquals(matchArrayList.get(3).getMatchId(), match4.getMatchId());
        assertEquals(matchArrayList.get(4).getMatchId(), match5.getMatchId());
    }

    @Test
    public void getMatchesBadRequestTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);

        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summoner.getPuuid()
                + "/ids?startTime="
                + "&endTime="
                + "&queue="
                + "&type="
                + "&start=" + 0
                + "&count=" + 5
                + "&api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl, String[].class)).thenThrow(HttpClientErrorException.BadRequest.class);

        assertThrows(BadRequestException.class, () -> riotApiService.getMatches(summoner.getName(), null, null, null, null, 0, 5));
    }

    @Test
    public void getMatchesUnauthorizedTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);

        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summoner.getPuuid()
                + "/ids?startTime="
                + "&endTime="
                + "&queue="
                + "&type="
                + "&start=" + 0
                + "&count=" + 5
                + "&api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl, String[].class)).thenThrow(HttpClientErrorException.Unauthorized.class);

        assertThrows(BadRequestException.class, () -> riotApiService.getMatches(summoner.getName(), null, null, null, null, 0, 5));
    }

    @Test
    public void getMatchesForbiddenTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);

        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summoner.getPuuid()
                + "/ids?startTime="
                + "&endTime="
                + "&queue="
                + "&type="
                + "&start=" + 0
                + "&count=" + 5
                + "&api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl, String[].class)).thenThrow(HttpClientErrorException.Forbidden.class);

        assertThrows(BadRequestException.class, () -> riotApiService.getMatches(summoner.getName(), null, null, null, null, 0, 5));
    }

    @Test
    public void getMatchesSummonerNotFoundTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(SummonerNotFoundException.class);

        assertThrows(SummonerNotFoundException.class, () -> riotApiService.getMatches(summoner.getName(), null, null, null, null, 0, 5));
    }

    @Test
    public void getMatchesMethodNotAllowedTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);

        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summoner.getPuuid()
                + "/ids?startTime="
                + "&endTime="
                + "&queue="
                + "&type="
                + "&start=" + 0
                + "&count=" + 5
                + "&api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl, String[].class)).thenThrow(HttpClientErrorException.MethodNotAllowed.class);

        assertThrows(MethodNotAllowed.class, () -> riotApiService.getMatches(summoner.getName(), null, null, null, null, 0, 5));
    }

    @Test
    public void getMatchesUnsupportedMediaTypeTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);

        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summoner.getPuuid()
                + "/ids?startTime="
                + "&endTime="
                + "&queue="
                + "&type="
                + "&start=" + 0
                + "&count=" + 5
                + "&api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl, String[].class)).thenThrow(HttpClientErrorException.UnsupportedMediaType.class);

        assertThrows(UnsupportedMediaType.class, () -> riotApiService.getMatches(summoner.getName(), null, null, null, null, 0, 5));
    }

    @Test
    public void getMatchesInternalServerErrorTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);

        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summoner.getPuuid()
                + "/ids?startTime="
                + "&endTime="
                + "&queue="
                + "&type="
                + "&start=" + 0
                + "&count=" + 5
                + "&api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl, String[].class)).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertThrows(InternalServerError.class, () -> riotApiService.getMatches(summoner.getName(), null, null, null, null, 0, 5));
    }

    @Test
    public void getMatchesBadGatewayTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);

        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summoner.getPuuid()
                + "/ids?startTime="
                + "&endTime="
                + "&queue="
                + "&type="
                + "&start=" + 0
                + "&count=" + 5
                + "&api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl, String[].class)).thenThrow(HttpServerErrorException.BadGateway.class);

        assertThrows(BadGateway.class, () -> riotApiService.getMatches(summoner.getName(), null, null, null, null, 0, 5));
    }

    @Test
    public void getMatchesServiceUnavailableTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);

        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summoner.getPuuid()
                + "/ids?startTime="
                + "&endTime="
                + "&queue="
                + "&type="
                + "&start=" + 0
                + "&count=" + 5
                + "&api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl, String[].class)).thenThrow(HttpServerErrorException.ServiceUnavailable.class);

        assertThrows(ServiceUnavailable.class, () -> riotApiService.getMatches(summoner.getName(), null, null, null, null, 0, 5));
    }

    @Test
    public void getMatchesGatewayTimeoutTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);

        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summoner.getPuuid()
                + "/ids?startTime="
                + "&endTime="
                + "&queue="
                + "&type="
                + "&start=" + 0
                + "&count=" + 5
                + "&api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl, String[].class)).thenThrow(HttpServerErrorException.GatewayTimeout.class);

        assertThrows(GatewayTimeout.class, () -> riotApiService.getMatches(summoner.getName(), null, null, null, null, 0, 5));
    }

    @Test
    public void getMatchesCounter() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);

        String apiUrl1 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match1.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl1, MatchDto.class)).thenReturn(matchDto1);

        String apiUrl2 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match2.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl2, MatchDto.class)).thenReturn(matchDto2);

        String apiUrl3 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match3.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl3, MatchDto.class)).thenReturn(matchDto3);

        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summoner.getPuuid()
                + "/ids?startTime="
                + "&endTime="
                + "&queue="
                + "&type="
                + "&start=" + 0
                + "&count=" + 3
                + "&api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl, String[].class)).thenReturn(matchList);

        ArrayList<Match> matchArrayList = riotApiService.getMatches(summoner.getName(), null, null, null, null, 0, 3);

        assertEquals(3, matchArrayList.size());
    }

    @Test
    public void getChampionsPlayedByNameTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);

        String apiUrl1 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match1.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl1, MatchDto.class)).thenReturn(matchDto1);

        String apiUrl2 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match2.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl2, MatchDto.class)).thenReturn(matchDto2);

        String apiUrl3 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match3.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl3, MatchDto.class)).thenReturn(matchDto3);

        String apiUrl4 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match4.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl4, MatchDto.class)).thenReturn(matchDto4);

        String apiUrl5 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match5.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl5, MatchDto.class)).thenReturn(matchDto5);

        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summoner.getPuuid()
                + "/ids?startTime="
                + "&endTime="
                + "&queue="
                + "&type="
                + "&start="
                + "&count=" + 20
                + "&api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl, String[].class)).thenReturn(matchList);

        ChampionsPlayed championsPlayedTest = riotApiService.getChampionsPlayedByName(summoner.getName());

        assertEquals(championsPlayed.getSummonerId(), championsPlayedTest.getSummonerId());
        assertEquals(championsPlayed.getSummonerName(), championsPlayedTest.getSummonerName());

        assertEquals(championsPlayed.getMostPlayedChampionId(), championsPlayedTest.getMostPlayedChampionId());
        assertEquals(championsPlayed.getMostPlayedChampionName(), championsPlayedTest.getMostPlayedChampionName());

        assertEquals(championsPlayed.getTotalGamesPlayed(), championsPlayedTest.getTotalGamesPlayed());
        assertEquals(championsPlayed.getTotalWins(), championsPlayedTest.getTotalWins());
        assertEquals(championsPlayed.getTotalLosses(), championsPlayedTest.getTotalLosses());
        assertEquals(championsPlayed.getWinRate(), championsPlayedTest.getWinRate());

        assertEquals(championsPlayed.getTotalKills(), championsPlayedTest.getTotalKills());
        assertEquals(championsPlayed.getTotalDeaths(), championsPlayedTest.getTotalDeaths());
        assertEquals(championsPlayed.getTotalAssists(), championsPlayedTest.getTotalAssists());
        assertEquals(championsPlayed.getKDA(), championsPlayedTest.getKDA());

        assertEquals(championsPlayed.getMostPlayedChampionId(), championsPlayedTest.getMostPlayedChampionId());
        assertEquals(championsPlayed.getMostPlayedChampionName(), championsPlayedTest.getMostPlayedChampionName());

        assertEquals(championsPlayed.getMostPlayedChampionCount(), championsPlayedTest.getMostPlayedChampionCount());
        assertEquals(championsPlayed.getMostPlayedChampionWins(), championsPlayedTest.getMostPlayedChampionWins());
        assertEquals(championsPlayed.getMostPlayedChampionLosses(), championsPlayedTest.getMostPlayedChampionLosses());
        assertEquals(championsPlayed.getMostPlayedChampionWinRate(), championsPlayedTest.getMostPlayedChampionWinRate());

        assertEquals(championsPlayed.getMostPlayedChampionKills(), championsPlayedTest.getMostPlayedChampionKills());
        assertEquals(championsPlayed.getMostPlayedChampionDeaths(), championsPlayedTest.getMostPlayedChampionDeaths());
        assertEquals(championsPlayed.getMostPlayedChampionAssists(), championsPlayedTest.getMostPlayedChampionAssists());
        assertEquals(championsPlayed.getMostPlayedChampionKDA(), championsPlayedTest.getMostPlayedChampionKDA());

        assertEquals(championsPlayed.getSecondMostPlayedChampionId(), championsPlayedTest.getSecondMostPlayedChampionId());
        assertEquals(championsPlayed.getSecondMostPlayedChampionName(), championsPlayedTest.getSecondMostPlayedChampionName());

        assertEquals(championsPlayed.getSecondMostPlayedChampionCount(), championsPlayedTest.getSecondMostPlayedChampionCount());
        assertEquals(championsPlayed.getSecondMostPlayedChampionWins(), championsPlayedTest.getSecondMostPlayedChampionWins());
        assertEquals(championsPlayed.getSecondMostPlayedChampionLosses(), championsPlayedTest.getSecondMostPlayedChampionLosses());
        assertEquals(championsPlayed.getSecondMostPlayedChampionWinRate(), championsPlayedTest.getSecondMostPlayedChampionWinRate());

        assertEquals(championsPlayed.getSecondMostPlayedChampionKills(), championsPlayedTest.getSecondMostPlayedChampionKills());
        assertEquals(championsPlayed.getSecondMostPlayedChampionDeaths(), championsPlayedTest.getSecondMostPlayedChampionDeaths());
        assertEquals(championsPlayed.getSecondMostPlayedChampionAssists(), championsPlayedTest.getSecondMostPlayedChampionAssists());
        assertEquals(championsPlayed.getSecondMostPlayedChampionKDA(), championsPlayedTest.getSecondMostPlayedChampionKDA());

        assertEquals(championsPlayed.getBestPerformingChampionId(), championsPlayedTest.getBestPerformingChampionId());
        assertEquals(championsPlayed.getBestPerformingChampionName(), championsPlayedTest.getBestPerformingChampionName());

        assertEquals(championsPlayed.getBestPerformingChampionCount(), championsPlayedTest.getBestPerformingChampionCount());
        assertEquals(championsPlayed.getBestPerformingChampionWins(), championsPlayedTest.getBestPerformingChampionWins());
        assertEquals(championsPlayed.getBestPerformingChampionLosses(), championsPlayedTest.getBestPerformingChampionLosses());
        assertEquals(championsPlayed.getBestPerformingChampionWinRate(), championsPlayedTest.getBestPerformingChampionWinRate());

        assertEquals(championsPlayed.getBestPerformingChampionKills(), championsPlayedTest.getBestPerformingChampionKills());
        assertEquals(championsPlayed.getBestPerformingChampionDeaths(), championsPlayedTest.getBestPerformingChampionDeaths());
        assertEquals(championsPlayed.getBestPerformingChampionAssists(), championsPlayedTest.getBestPerformingChampionAssists());
        assertEquals(championsPlayed.getBestPerformingChampionKDA(), championsPlayedTest.getBestPerformingChampionKDA());

        assertEquals(championsPlayed.getWorstPerformingChampionId(), championsPlayedTest.getWorstPerformingChampionId());
        assertEquals(championsPlayed.getWorstPerformingChampionName(), championsPlayedTest.getWorstPerformingChampionName());

        assertEquals(championsPlayed.getWorstPerformingChampionCount(), championsPlayedTest.getWorstPerformingChampionCount());
        assertEquals(championsPlayed.getWorstPerformingChampionWins(), championsPlayedTest.getWorstPerformingChampionWins());
        assertEquals(championsPlayed.getWorstPerformingChampionLosses(), championsPlayedTest.getWorstPerformingChampionLosses());
        assertEquals(championsPlayed.getWorstPerformingChampionWinRate(), championsPlayedTest.getWorstPerformingChampionWinRate());

        assertEquals(championsPlayed.getWorstPerformingChampionKills(), championsPlayedTest.getWorstPerformingChampionKills());
        assertEquals(championsPlayed.getWorstPerformingChampionDeaths(), championsPlayedTest.getWorstPerformingChampionDeaths());
        assertEquals(championsPlayed.getWorstPerformingChampionAssists(), championsPlayedTest.getWorstPerformingChampionAssists());
        assertEquals(championsPlayed.getWorstPerformingChampionKDA(), championsPlayedTest.getWorstPerformingChampionKDA());
    }

    @Test
    public void getGameModesPlayedByNameTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenReturn(summonerDto);

        String apiUrl1 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match1.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl1, MatchDto.class)).thenReturn(matchDto1);

        String apiUrl2 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match2.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl2, MatchDto.class)).thenReturn(matchDto2);

        String apiUrl3 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match3.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl3, MatchDto.class)).thenReturn(matchDto3);

        String apiUrl4 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match4.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl4, MatchDto.class)).thenReturn(matchDto4);

        String apiUrl5 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match5.getMatchId() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl5, MatchDto.class)).thenReturn(matchDto5);

        Mockito.when(queueRepository.findById(400)).thenReturn(Optional.of(new LOLQueue(400, "Summoner's Rift", "5v5 Draft Pick games")));
        Mockito.when(queueRepository.findById(420)).thenReturn(Optional.of(new LOLQueue(420, "Summoner's Rift", "5v5 Ranked Solo games")));

        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summoner.getPuuid()
                + "/ids?startTime="
                + "&endTime="
                + "&queue="
                + "&type="
                + "&start="
                + "&count=" + 20
                + "&api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrl, String[].class)).thenReturn(matchList);

        GameModesPlayed gameModesPlayedTest = riotApiService.getGameModesPlayedByName(summoner.getName());

        assertEquals(gameModesPlayed.getSummonerId(), gameModesPlayedTest.getSummonerId());
        assertEquals(gameModesPlayed.getSummonerName(), gameModesPlayedTest.getSummonerName());

        assertEquals(gameModesPlayed.getMostPlayedGameModeId(), gameModesPlayedTest.getMostPlayedGameModeId());
        assertEquals(gameModesPlayed.getMostPlayedGameModeName(), gameModesPlayedTest.getMostPlayedGameModeName());

        assertEquals(gameModesPlayed.getTotalGamesPlayed(), gameModesPlayedTest.getTotalGamesPlayed());
        assertEquals(gameModesPlayed.getTotalWins(), gameModesPlayedTest.getTotalWins());
        assertEquals(gameModesPlayed.getTotalLosses(), gameModesPlayedTest.getTotalLosses());
        assertEquals(gameModesPlayed.getWinRate(), gameModesPlayedTest.getWinRate());

        assertEquals(gameModesPlayed.getTotalKills(), gameModesPlayedTest.getTotalKills());
        assertEquals(gameModesPlayed.getTotalDeaths(), gameModesPlayedTest.getTotalDeaths());
        assertEquals(gameModesPlayed.getTotalAssists(), gameModesPlayedTest.getTotalAssists());
        assertEquals(gameModesPlayed.getKDA(), gameModesPlayedTest.getKDA());

        assertEquals(gameModesPlayed.getMostPlayedGameModeId(), gameModesPlayedTest.getMostPlayedGameModeId());
        assertEquals(gameModesPlayed.getMostPlayedGameModeName(), gameModesPlayedTest.getMostPlayedGameModeName());

        assertEquals(gameModesPlayed.getMostPlayedGameModeCount(), gameModesPlayedTest.getMostPlayedGameModeCount());
        assertEquals(gameModesPlayed.getMostPlayedGameModeWins(), gameModesPlayedTest.getMostPlayedGameModeWins());
        assertEquals(gameModesPlayed.getMostPlayedGameModeLosses(), gameModesPlayedTest.getMostPlayedGameModeLosses());
        assertEquals(gameModesPlayed.getMostPlayedGameModeWinRate(), gameModesPlayedTest.getMostPlayedGameModeWinRate());

        assertEquals(gameModesPlayed.getMostPlayedGameModeKills(), gameModesPlayedTest.getMostPlayedGameModeKills());
        assertEquals(gameModesPlayed.getMostPlayedGameModeDeaths(), gameModesPlayedTest.getMostPlayedGameModeDeaths());
        assertEquals(gameModesPlayed.getMostPlayedGameModeAssists(), gameModesPlayedTest.getMostPlayedGameModeAssists());
        assertEquals(gameModesPlayed.getMostPlayedGameModeKDA(), gameModesPlayedTest.getMostPlayedGameModeKDA());

        assertEquals(gameModesPlayed.getSecondMostPlayedGameModeId(), gameModesPlayedTest.getSecondMostPlayedGameModeId());
        assertEquals(gameModesPlayed.getSecondMostPlayedGameModeName(), gameModesPlayedTest.getSecondMostPlayedGameModeName());

        assertEquals(gameModesPlayed.getSecondMostPlayedGameModeCount(), gameModesPlayedTest.getSecondMostPlayedGameModeCount());
        assertEquals(gameModesPlayed.getSecondMostPlayedGameModeWins(), gameModesPlayedTest.getSecondMostPlayedGameModeWins());
        assertEquals(gameModesPlayed.getSecondMostPlayedGameModeLosses(), gameModesPlayedTest.getSecondMostPlayedGameModeLosses());
        assertEquals(gameModesPlayed.getSecondMostPlayedGameModeWinRate(), gameModesPlayedTest.getSecondMostPlayedGameModeWinRate());

        assertEquals(gameModesPlayed.getSecondMostPlayedGameModeKills(), gameModesPlayedTest.getSecondMostPlayedGameModeKills());
        assertEquals(gameModesPlayed.getSecondMostPlayedGameModeDeaths(), gameModesPlayedTest.getSecondMostPlayedGameModeDeaths());
        assertEquals(gameModesPlayed.getSecondMostPlayedGameModeAssists(), gameModesPlayedTest.getSecondMostPlayedGameModeAssists());
        assertEquals(gameModesPlayed.getSecondMostPlayedGameModeKDA(), gameModesPlayedTest.getSecondMostPlayedGameModeKDA());

        assertEquals(gameModesPlayed.getBestPerformingGameModeId(), gameModesPlayedTest.getBestPerformingGameModeId());
        assertEquals(gameModesPlayed.getBestPerformingGameModeName(), gameModesPlayedTest.getBestPerformingGameModeName());

        assertEquals(gameModesPlayed.getBestPerformingGameModeCount(), gameModesPlayedTest.getBestPerformingGameModeCount());
        assertEquals(gameModesPlayed.getBestPerformingGameModeWins(), gameModesPlayedTest.getBestPerformingGameModeWins());
        assertEquals(gameModesPlayed.getBestPerformingGameModeLosses(), gameModesPlayedTest.getBestPerformingGameModeLosses());
        assertEquals(gameModesPlayed.getBestPerformingGameModeWinRate(), gameModesPlayedTest.getBestPerformingGameModeWinRate());

        assertEquals(gameModesPlayed.getBestPerformingGameModeKills(), gameModesPlayedTest.getBestPerformingGameModeKills());
        assertEquals(gameModesPlayed.getBestPerformingGameModeDeaths(), gameModesPlayedTest.getBestPerformingGameModeDeaths());
        assertEquals(gameModesPlayed.getBestPerformingGameModeAssists(), gameModesPlayedTest.getBestPerformingGameModeAssists());
        assertEquals(gameModesPlayed.getBestPerformingGameModeKDA(), gameModesPlayedTest.getBestPerformingGameModeKDA());

        assertEquals(gameModesPlayed.getWorstPerformingGameModeId(), gameModesPlayedTest.getWorstPerformingGameModeId());
        assertEquals(gameModesPlayed.getWorstPerformingGameModeName(), gameModesPlayedTest.getWorstPerformingGameModeName());

        assertEquals(gameModesPlayed.getWorstPerformingGameModeCount(), gameModesPlayedTest.getWorstPerformingGameModeCount());
        assertEquals(gameModesPlayed.getWorstPerformingGameModeWins(), gameModesPlayedTest.getWorstPerformingGameModeWins());
        assertEquals(gameModesPlayed.getWorstPerformingGameModeLosses(), gameModesPlayedTest.getWorstPerformingGameModeLosses());
        assertEquals(gameModesPlayed.getWorstPerformingGameModeWinRate(), gameModesPlayedTest.getWorstPerformingGameModeWinRate());

        assertEquals(gameModesPlayed.getWorstPerformingGameModeKills(), gameModesPlayedTest.getWorstPerformingGameModeKills());
        assertEquals(gameModesPlayed.getWorstPerformingGameModeDeaths(), gameModesPlayedTest.getWorstPerformingGameModeDeaths());
        assertEquals(gameModesPlayed.getWorstPerformingGameModeAssists(), gameModesPlayedTest.getWorstPerformingGameModeAssists());
        assertEquals(gameModesPlayed.getWorstPerformingGameModeKDA(), gameModesPlayedTest.getWorstPerformingGameModeKDA());
    }
}