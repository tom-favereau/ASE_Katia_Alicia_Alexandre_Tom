package com.project.ase_project.unit.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ase_project.exception.*;
import com.project.ase_project.model.clean.grade.Grade;
import com.project.ase_project.model.clean.league.League;
import com.project.ase_project.model.clean.match.Match;
import com.project.ase_project.model.clean.summary.Summary;
import com.project.ase_project.model.clean.summoner.Summoner;
import com.project.ase_project.model.dto.league.LeagueDto;
import com.project.ase_project.model.dto.match.MatchDto;
import com.project.ase_project.model.dto.summoner.SummonerDto;
import com.project.ase_project.repository.GradeRepository;
import com.project.ase_project.repository.MatchRepository;
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

    @BeforeAll
    public static void matchSetUp() throws IOException, URISyntaxException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new URI("https://europe.api.riotgames.com/lol/match/v5/matches/EUW1_6760205418?api_key=RGAPI-7824e8d4-5ed0-4244-8b26-67ba3e260cc2").toURL());
        matchDto = objectMapper.readValue(jsonNode.toString(), MatchDto.class);
        match = matchDto.toMatch();
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
    public void getSummonerByNameTooManyRequestsTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + "Belugafurtif" + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, SummonerDto.class)).thenThrow(HttpClientErrorException.TooManyRequests.class);

        assertThrows(RateLimitExceededException.class, () -> riotApiService.getSummonerByName("Belugafurtif"));
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
    public void getMatchByIdNotFoundTest() {
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match.getMatchId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, MatchDto.class)).thenThrow(HttpClientErrorException.NotFound.class);

        assertThrows(MatchNotFoundException.class, () -> riotApiService.getMatchById(match.getMatchId()));
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
    public void getMatchByIdTooManyRequestsTest() {
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/" + match.getMatchId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, MatchDto.class)).thenThrow(HttpClientErrorException.TooManyRequests.class);

        assertThrows(RateLimitExceededException.class, () -> riotApiService.getMatchById(match.getMatchId()));
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
    public void getRankByIdTooManyRequestsTest() {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDto.getId() + "?api_key=" + apiKey;

        Mockito.when(restTemplate.getForObject(apiUrl, LeagueDto[].class)).thenThrow(HttpClientErrorException.TooManyRequests.class);

        assertThrows(RateLimitExceededException.class, () -> riotApiService.getRankById(summonerDto.getId()));
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
    public void getSummaryByNameRateLimitExceededTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpClientErrorException.TooManyRequests.class);

        assertThrows(RateLimitExceededException.class, () -> riotApiService.getSummaryByName(summonerDto.getName()));
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
    public void postGradeRateLimitExceededTest() {
        String apiUrlSummoner = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerDto.getName() + "?api_key=" + apiKey;
        Mockito.when(restTemplate.getForObject(apiUrlSummoner, SummonerDto.class)).thenThrow(HttpClientErrorException.TooManyRequests.class);

        assertThrows(RateLimitExceededException.class, () -> riotApiService.postGrade("Belugafurtif", 5));
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
}