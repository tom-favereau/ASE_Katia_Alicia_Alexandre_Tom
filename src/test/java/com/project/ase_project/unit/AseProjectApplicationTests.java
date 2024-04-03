package com.project.ase_project.unit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ase_project.AseProjectApplication;
import com.project.ase_project.exception.*;
import com.project.ase_project.model.clean.MostPlayedChampions.ChampionsPlayed;
import com.project.ase_project.model.clean.MostPlayedGameModes.GameModesPlayed;
import com.project.ase_project.model.clean.league.League;
import com.project.ase_project.model.clean.match.Match;
import com.project.ase_project.model.clean.summary.Summary;
import com.project.ase_project.model.clean.summoner.Summoner;
import com.project.ase_project.model.dto.match.MatchDto;
import com.project.ase_project.service.RiotApiService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AseProjectApplication.class)
//@WebMvcTest(AseProjectApplication.class)
@AutoConfigureMockMvc
class AseProjectApplicationTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    RiotApiService riotApiService;

    static MatchDto matchDto;
    static Match match;

    Summoner summoner = new Summoner(
            "Belugafurtif",
            456,
            123,
            0,
            0,
            "",
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
    ChampionsPlayed championsPlayed = new ChampionsPlayed(
            "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ",
            "Belugafurtif",

            5,
            3,
            2,
            0.6f,

            10,
            5,
            6,
            3.2f,

            83,
            "Aatrox",

            3,
            2,
            1,
            0.66f,

            7,
            2,
            2,
            4.5f,

            84,
            "Ahri",

            2,
            1,
            1,
            0.5f,

            3,
            3,
            4,
            2.33f,

            83,
            "Aatrox",

            3,
            2,
            1,
            0.66f,

            7,
            2,
            2,
            4.5f,

            84,
            "Ahri",

            2,
            1,
            1,
            0.5f,

            3,
            3,
            4,
            2.33f
    );

    GameModesPlayed gameModesPlayed = new GameModesPlayed(
            "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ",
            "Belugafurtif",

            5,
            3,
            2,
            0.6f,

            10,
            5,
            6,
            3.2f,

            83,
            "ARAM",

            3,
            2,
            1,
            0.66f,

            7,
            2,
            2,
            4.5f,

            84,
            "Summoner's Rift 5vs5 SOLO/DUO",

            2,
            1,
            1,
            0.5f,

            3,
            3,
            4,
            2.33f,

            83,
            "ARAM",

            3,
            2,
            1,
            0.66f,

            7,
            2,
            2,
            4.5f,

            84,
            "Summoner's Rift 5vs5 SOLO/DUO",

            2,
            1,
            1,
            0.5f,

            3,
            3,
            4,
            2.33f
    );
    static MatchDto matchDto1;
    static Match match1;
    static MatchDto matchDto2;
    static Match match2;
    static MatchDto matchDto3;
    static Match match3;
    static MatchDto matchDto4;
    static Match match4;
    static MatchDto matchDto5;
    static Match match5;
    static ArrayList<Match> matchList;
    
    @BeforeAll
    public static void matchSetUp() throws IOException, URISyntaxException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new URI("https://europe.api.riotgames.com/lol/match/v5/matches/EUW1_6760205418?api_key=RGAPI-7824e8d4-5ed0-4244-8b26-67ba3e260cc2").toURL());
        matchDto = objectMapper.readValue(jsonNode.toString(), MatchDto.class);
        match = matchDto.toMatch();

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

        matchList = new ArrayList<>(List.of(match1, match2, match3, match4, match5));
    }

    @AfterAll
    public static void tearDown() {
    }

    @Test
    public void testGetSummaryData() throws Exception {
        Mockito.when(riotApiService.getSummaryByName("Belugafurtif")).thenReturn(summary);

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
                .andExpect(jsonPath("$.average").value(0))
                .andExpect(jsonPath("$.cardinal").value(0)) ;
    }

    @Test
    public void testGetSummaryBadRequest() throws Exception {
        Mockito.when(riotApiService.getSummaryByName("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")).thenThrow(BadRequestException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/summary/F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadRequestException));
    }

    @Test
    public void testGetSummarySummonerNotFound() throws Exception {
        Mockito.when(riotApiService.getSummaryByName("Belugafurti")).thenThrow(SummonerNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/summary/Belugafurti")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof SummonerNotFoundException));
    }

    @Test
    public void testGetSummaryLeaguesNotFound() throws Exception {
        Mockito.when(riotApiService.getSummaryByName("afaureve")).thenThrow(LeaguesNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/riot/summary/afaureve")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof LeaguesNotFoundException));
    }

    @Test
    public void testGetSummaryMethodNotAllowed() throws Exception {
        Mockito.when(riotApiService.getSummaryByName("afaureve")).thenThrow(MethodNotAllowed.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/riot/summary/afaureve")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testGetSummaryUnsupportedMediaType() throws Exception {
        Mockito.when(riotApiService.getSummaryByName("afaureve")).thenThrow(UnsupportedMediaType.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/riot/summary/afaureve")
                        .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedMediaType));
    }

    @Test
    public void testGetSummaryRateLimitExceeded() throws Exception {
        Mockito.when(riotApiService.getSummaryByName("afaureve")).thenThrow(RateLimitExceededException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/riot/summary/afaureve")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isTooManyRequests())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RateLimitExceededException));
    }

    @Test
    public void testGetSummaryInternalServerError() throws Exception {
        Mockito.when(riotApiService.getSummaryByName("afaureve")).thenThrow(InternalServerError.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/riot/summary/afaureve")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InternalServerError));
    }

    @Test
    public void testGetSummaryBadGateway() throws Exception {
        Mockito.when(riotApiService.getSummaryByName("afaureve")).thenThrow(BadGateway.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/riot/summary/afaureve")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadGateway())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadGateway));
    }

    @Test
    public void testGetSummaryServiceUnavailable() throws Exception {
        Mockito.when(riotApiService.getSummaryByName("afaureve")).thenThrow(ServiceUnavailable.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/riot/summary/afaureve")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isServiceUnavailable())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ServiceUnavailable));
    }

    @Test
    public void testGetSummaryGatewayTimeout() throws Exception {
        Mockito.when(riotApiService.getSummaryByName("afaureve")).thenThrow(GatewayTimeout.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/riot/summary/afaureve")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isGatewayTimeout())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof GatewayTimeout));
    }

    @Test
    public void testGetSummaryNoArgument() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/summary/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));
    }

    @Test
    public void testGetSummonerData() throws Exception {
        Mockito.when(riotApiService.getSummonerByName("Belugafurtif")).thenReturn(summoner);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/summoners/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Belugafurtif"))
                .andExpect(jsonPath("$.profileIconId").value(456))
                .andExpect(jsonPath("$.summonerLevel").value(123))
                .andExpect(jsonPath("$.average").value(0))
                .andExpect(jsonPath("$.cardinal").value(0))
                .andExpect(jsonPath("$.puuid").value(""))
                .andExpect(jsonPath("$.id").value("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ"));
    }

    @Test
    public void testGetSummonerBadRequest() throws Exception {
        Mockito.when(riotApiService.getSummonerByName("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")).thenThrow(BadRequestException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/summoners/F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadRequestException));
    }

    @Test
    public void testGetSummonerNotFound() throws Exception {
        Mockito.when(riotApiService.getSummonerByName("Belugafurti")).thenThrow(SummonerNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/summoners/Belugafurti")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof SummonerNotFoundException));
    }

    @Test
    public void testGetSummonerMethodNotAllowed() throws Exception {
        Mockito.when(riotApiService.getSummonerByName("Belugafurtif")).thenThrow(MethodNotAllowed.class);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/summoners/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testGetSummonerUnsupportedMediaType() throws Exception {
        Mockito.when(riotApiService.getSummonerByName("Belugafurtif")).thenThrow(UnsupportedMediaType.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/summoners/Belugafurtif")
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedMediaType));
    }

    @Test
    public void testGetSummonerRateLimitExceeded() throws Exception {
        Mockito.when(riotApiService.getSummonerByName("Belugafurtif")).thenThrow(RateLimitExceededException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/summoners/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isTooManyRequests())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RateLimitExceededException));
    }

    @Test
    public void testGetSummonerInternalServerError() throws Exception {
        Mockito.when(riotApiService.getSummonerByName("Belugafurtif")).thenThrow(InternalServerError.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/summoners/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InternalServerError));
    }

    @Test
    public void testGetSummonerBadGateway() throws Exception {
        Mockito.when(riotApiService.getSummonerByName("Belugafurtif")).thenThrow(BadGateway.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/summoners/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadGateway())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadGateway));
    }

    @Test
    public void testGetSummonerServiceUnavailable() throws Exception {
        Mockito.when(riotApiService.getSummonerByName("Belugafurtif")).thenThrow(ServiceUnavailable.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/summoners/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isServiceUnavailable())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ServiceUnavailable));
    }

    @Test
    public void testGetSummonerGatewayTimeout() throws Exception {
        Mockito.when(riotApiService.getSummonerByName("Belugafurtif")).thenThrow(GatewayTimeout.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/summoners/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isGatewayTimeout())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof GatewayTimeout));
    }

    @Test
    public void testGetSummonerNoArgument() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/summoners/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));
    }

    @Test
    public void testGetRankData() throws Exception {
        Mockito.when(riotApiService.getRankById("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")).thenReturn(leagues);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/rank/F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].leagueId").value("13d1771a-99a8-4d56-ad04-5ced66978f76"))
                .andExpect(jsonPath("$[0].summonerId").value("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ"))
                .andExpect(jsonPath("$[0].summonerName").value("Belugafurtif"))
                .andExpect(jsonPath("$[0].queueType").value("RANKED_FLEX_SR"))
                .andExpect(jsonPath("$[0].tier").value("DIAMOND"))
                .andExpect(jsonPath("$[0].rank").value("I"))
                .andExpect(jsonPath("$[0].leaguePoints").value(10))
                .andExpect(jsonPath("$[0].wins").value(50))
                .andExpect(jsonPath("$[0].losses").value(50))
                .andExpect(jsonPath("$[1].leagueId").value("d597c614-efac-4470-a360-e798620fe4e3"))
                .andExpect(jsonPath("$[1].summonerId").value("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ"))
                .andExpect(jsonPath("$[1].summonerName").value("Belugafurtif"))
                .andExpect(jsonPath("$[1].queueType").value("RANKED_SOLO_5x5"))
                .andExpect(jsonPath("$[1].tier").value("GOLD"))
                .andExpect(jsonPath("$[1].rank").value("III"))
                .andExpect(jsonPath("$[1].leaguePoints").value(58))
                .andExpect(jsonPath("$[1].wins").value(126))
                .andExpect(jsonPath("$[1].losses").value(78));
    }

    @Test
    public void testGetRankBadRequest() throws Exception {
        Mockito.when(riotApiService.getRankById("Belugafurtif")).thenThrow(BadRequestException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/rank/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadRequestException));
    }

    @Test
    public void testGetRankNotFound() throws Exception {
        Mockito.when(riotApiService.getRankById("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")).thenThrow(LeaguesNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/rank/F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof LeaguesNotFoundException));
    }

    @Test
    public void testGetRankMethodNotAllowed() throws Exception {
        Mockito.when(riotApiService.getRankById("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")).thenThrow(MethodNotAllowed.class);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/rank/F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testGetRankUnsupportedMediaType() throws Exception {
        Mockito.when(riotApiService.getRankById("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")).thenThrow(UnsupportedMediaType.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/rank/F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedMediaType));
    }

    @Test
    public void testGetRankRateLimitExceeded() throws Exception {
        Mockito.when(riotApiService.getRankById("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")).thenThrow(RateLimitExceededException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/rank/F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isTooManyRequests())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RateLimitExceededException));
    }

    @Test
    public void testGetRankInternalServerError() throws Exception {
        Mockito.when(riotApiService.getRankById("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")).thenThrow(InternalServerError.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/rank/F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InternalServerError));
    }

    @Test
    public void testGetRankBadGateway() throws Exception {
        Mockito.when(riotApiService.getRankById("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")).thenThrow(BadGateway.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/rank/F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadGateway())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadGateway));
    }

    @Test
    public void testGetRankServiceUnavailable() throws Exception {
        Mockito.when(riotApiService.getRankById("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")).thenThrow(ServiceUnavailable.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/rank/F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isServiceUnavailable())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ServiceUnavailable));
    }

    @Test
    public void testGetRankGatewayTimeout() throws Exception {
        Mockito.when(riotApiService.getRankById("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")).thenThrow(GatewayTimeout.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/rank/F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isGatewayTimeout())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof GatewayTimeout));
    }

    @Test
    public void testGetRankNoArgument() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/rank/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));
    }

    @Test
    public void testGetMatchData() throws Exception {
        Mockito.when(riotApiService.getMatchById("EUW1_6760205418")).thenReturn(match);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/matches/EUW1_6760205418")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.matchId").value("EUW1_6760205418"));
    }

    @Test
    public void testGetMatchBadRequest() throws Exception {
        Mockito.when(riotApiService.getMatchById("EUW1_6760205418")).thenThrow(BadRequestException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/matches/EUW1_6760205418")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadRequestException));
    }

    @Test
    public void testGetMatchNotFound() throws Exception {
        Mockito.when(riotApiService.getMatchById("EUW1_6760205418")).thenThrow(MatchNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/matches/EUW1_6760205418")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MatchNotFoundException));
    }

    @Test
    public void testGetMatchMethodNotAllowed() throws Exception {
        Mockito.when(riotApiService.getMatchById("EUW1_6760205418")).thenThrow(MethodNotAllowed.class);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/matches/EUW1_6760205418")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testGetMatchUnsupportedMediaType() throws Exception {
        Mockito.when(riotApiService.getMatchById("EUW1_6760205418")).thenThrow(UnsupportedMediaType.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/matches/EUW1_6760205418")
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedMediaType));
    }

    @Test
    public void testGetMatchRateLimitExceeded() throws Exception {
        Mockito.when(riotApiService.getMatchById("EUW1_6760205418")).thenThrow(RateLimitExceededException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/matches/EUW1_6760205418")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isTooManyRequests())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RateLimitExceededException));
    }

    @Test
    public void testGetMatchInternalServerError() throws Exception {
        Mockito.when(riotApiService.getMatchById("EUW1_6760205418")).thenThrow(InternalServerError.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/matches/EUW1_6760205418")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InternalServerError));
    }

    @Test
    public void testGetMatchBadGateway() throws Exception {
        Mockito.when(riotApiService.getMatchById("EUW1_6760205418")).thenThrow(BadGateway.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/matches/EUW1_6760205418")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadGateway())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadGateway));
    }

    @Test
    public void testGetMatchServiceUnavailable() throws Exception {
        Mockito.when(riotApiService.getMatchById("EUW1_6760205418")).thenThrow(ServiceUnavailable.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/matches/EUW1_6760205418")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isServiceUnavailable())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ServiceUnavailable));
    }

    @Test
    public void testGetMatchGatewayTimeout() throws Exception {
        Mockito.when(riotApiService.getMatchById("EUW1_6760205418")).thenThrow(GatewayTimeout.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/matches/EUW1_6760205418")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isGatewayTimeout())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof GatewayTimeout));
    }

    @Test
    public void testGetMatchNoArgument() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/matches/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));
    }

    @Test
    public void testPostGradeData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/grade/Belugafurtif/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Votre note a été postée avec succès !"));

        verify(riotApiService, times(1)).postGrade(eq("Belugafurtif"), eq(3));
    }

    @Test
    public void testPostGradeBadRequest() throws Exception {
        Mockito.doThrow(BadRequestException.class).when(riotApiService).postGrade("Belugafurtif", 3);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/grade/Belugafurtif/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadRequestException));
    }

    @Test
    public void testPostSummonerNotFound() throws Exception {
        Mockito.doThrow(SummonerNotFoundException.class).when(riotApiService).postGrade("Belugafurti", 3);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/grade/Belugafurti/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof SummonerNotFoundException));
    }

    @Test
    public void testPostGradeMethodNotAllowed() throws Exception {
        Mockito.doThrow(MethodNotAllowed.class).when(riotApiService).postGrade("Belugafurtif", 3);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/grade/Belugafurtif/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testPostGradeUnsupportedMediaType() throws Exception {
        Mockito.doThrow(UnsupportedMediaType.class).when(riotApiService).postGrade("Belugafurtif", 3);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/grade/Belugafurtif/3")
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedMediaType));
    }

    @Test
    public void testPostGradeRateLimitExceeded() throws Exception {
        Mockito.doThrow(RateLimitExceededException.class).when(riotApiService).postGrade("Belugafurtif", 3);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/grade/Belugafurtif/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isTooManyRequests())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RateLimitExceededException));
    }
    @Test
    public void testPostGradeInternalServerError() throws Exception {
        Mockito.doThrow(InternalServerError.class).when(riotApiService).postGrade("Belugafurtif", 3);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/grade/Belugafurtif/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InternalServerError));
    }

    @Test
    public void testPostGradeBadGateway() throws Exception {
        Mockito.doThrow(BadGateway.class).when(riotApiService).postGrade("Belugafurtif", 3);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/grade/Belugafurtif/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadGateway())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadGateway));
    }

    @Test
    public void testPostGradeServiceUnavailable() throws Exception {
        Mockito.doThrow(ServiceUnavailable.class).when(riotApiService).postGrade("Belugafurtif", 3);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/grade/Belugafurtif/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isServiceUnavailable())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ServiceUnavailable));
    }

    @Test
    public void testPostGradeGatewayTimeout() throws Exception {
        Mockito.doThrow(GatewayTimeout.class).when(riotApiService).postGrade("Belugafurtif", 3);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/grade/Belugafurtif/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isGatewayTimeout())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof GatewayTimeout));
    }

    @Test
    public void testPostGradeNumberFormat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/grade/Belugafurtif/trois")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));
    }

    @Test
    public void testPostGradeArgumentInterval() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/grade/Belugafurtif/6")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));
    }

    @Test
    public void testPostGradeNoSummonerName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/grade/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));
    }

    @Test
    public void testPostGradeNoGrade() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/grade/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));
    }

    @Test
    public void testGetGradeNoArgument() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/grade/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));
    }

    @Test
    public void testGetChampionsPlayed() throws Exception {
        Mockito.when(riotApiService.getChampionsPlayedByName("Belugafurtif")).thenReturn(championsPlayed);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/championsPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summonerId").value(summoner.getId()));
    }

    @Test
    public void testGetChampionsPlayedBadRequest() throws Exception {
        Mockito.when(riotApiService.getChampionsPlayedByName("Belugafurtif")).thenThrow(BadRequestException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/championsPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadRequestException));
    }

    @Test
    public void testGetChampionsPlayedSummonerNotFound() throws Exception {
        Mockito.when(riotApiService.getChampionsPlayedByName("Belugafurti")).thenThrow(SummonerNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/championsPlayed/Belugafurti")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof SummonerNotFoundException));
    }

    @Test
    public void testGetChampionsPlayedMatchNotFound() throws Exception {
        Mockito.when(riotApiService.getChampionsPlayedByName("afaureve")).thenThrow(MatchNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/championsPlayed/afaureve")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MatchNotFoundException));
    }

    @Test
    public void testGetChampionsPlayedMethodNotAllowed() throws Exception {
        Mockito.when(riotApiService.getChampionsPlayedByName("Belugafurtif")).thenThrow(MethodNotAllowed.class);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/championsPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testGetChampionsPlayedUnsupportedMediaType() throws Exception {
        Mockito.when(riotApiService.getChampionsPlayedByName("Belugafurtif")).thenThrow(UnsupportedMediaType.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/championsPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedMediaType));
    }

    @Test
    public void testGetChampionsPlayedRateLimitExceeded() throws Exception {
        Mockito.when(riotApiService.getChampionsPlayedByName("Belugafurtif")).thenThrow(RateLimitExceededException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/championsPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isTooManyRequests())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RateLimitExceededException));
    }

    @Test
    public void testGetChampionsPlayedInternalServerError() throws Exception {
        Mockito.when(riotApiService.getChampionsPlayedByName("Belugafurtif")).thenThrow(InternalServerError.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/championsPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InternalServerError));
    }

    @Test
    public void testGetChampionsPlayedBadGateway() throws Exception {
        Mockito.when(riotApiService.getChampionsPlayedByName("Belugafurtif")).thenThrow(BadGateway.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/championsPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadGateway())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadGateway));
    }

    @Test
    public void testGetChampionsPlayedServiceUnavailable() throws Exception {
        Mockito.when(riotApiService.getChampionsPlayedByName("Belugafurtif")).thenThrow(ServiceUnavailable.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/championsPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isServiceUnavailable())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ServiceUnavailable));
    }

    @Test
    public void testGetChampionsPlayedGatewayTimeout() throws Exception {
        Mockito.when(riotApiService.getChampionsPlayedByName("Belugafurtif")).thenThrow(GatewayTimeout.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/championsPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isGatewayTimeout())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof GatewayTimeout));
    }

    @Test
    public void testGetChampionsPlayedNoArgument() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/championsPlayed/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));
    }

    @Test
    public void testGetGameModesPlayed() throws Exception {
        Mockito.when(riotApiService.getGameModesPlayedByName("Belugafurtif")).thenReturn(gameModesPlayed);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/gameModesPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.summonerId").value(summoner.getId()));
    }

    @Test
    public void testGetGameModesPlayedBadRequest() throws Exception {
        Mockito.when(riotApiService.getGameModesPlayedByName("Belugafurtif")).thenThrow(BadRequestException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/gameModesPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadRequestException));
    }

    @Test
    public void testGetGameModesPlayedSummonerNotFound() throws Exception {
        Mockito.when(riotApiService.getGameModesPlayedByName("Belugafurti")).thenThrow(SummonerNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/gameModesPlayed/Belugafurti")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof SummonerNotFoundException));
    }

    @Test
    public void testGetGameModesPlayedMatchNotFound() throws Exception {
        Mockito.when(riotApiService.getGameModesPlayedByName("afaureve")).thenThrow(MatchNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/gameModesPlayed/afaureve")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MatchNotFoundException));
    }

    @Test
    public void testGetGameModesPlayedMethodNotAllowed() throws Exception {
        Mockito.when(riotApiService.getGameModesPlayedByName("Belugafurtif")).thenThrow(MethodNotAllowed.class);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/riot/gameModesPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testGetGameModesPlayedUnsupportedMediaType() throws Exception {
        Mockito.when(riotApiService.getGameModesPlayedByName("Belugafurtif")).thenThrow(UnsupportedMediaType.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/gameModesPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedMediaType));
    }

    @Test
    public void testGetGameModesPlayedRateLimitExceeded() throws Exception {
        Mockito.when(riotApiService.getGameModesPlayedByName("Belugafurtif")).thenThrow(RateLimitExceededException.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/gameModesPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isTooManyRequests())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RateLimitExceededException));
    }

    @Test
    public void testGetGameModesPlayedInternalServerError() throws Exception {
        Mockito.when(riotApiService.getGameModesPlayedByName("Belugafurtif")).thenThrow(InternalServerError.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/gameModesPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InternalServerError));
    }

    @Test
    public void testGetGameModesPlayedBadGateway() throws Exception {
        Mockito.when(riotApiService.getGameModesPlayedByName("Belugafurtif")).thenThrow(BadGateway.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/gameModesPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadGateway())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadGateway));
    }

    @Test
    public void testGetGameModesPlayedServiceUnavailable() throws Exception {
        Mockito.when(riotApiService.getGameModesPlayedByName("Belugafurtif")).thenThrow(ServiceUnavailable.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/gameModesPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isServiceUnavailable())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ServiceUnavailable));
    }

    @Test
    public void testGetGameModesPlayedGatewayTimeout() throws Exception {
        Mockito.when(riotApiService.getGameModesPlayedByName("Belugafurtif")).thenThrow(GatewayTimeout.class);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/gameModesPlayed/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isGatewayTimeout())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof GatewayTimeout));
    }

    @Test
    public void testGetGameModesPlayedNoArgument() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/gameModesPlayed/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));
    }

    @Test
    public void getLastMatches() throws Exception {
        Mockito.when(riotApiService.getMatches("Belugafurtif", null, null, null, null, null, 5)).thenReturn(matchList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/riot/lastMatches/Belugafurtif")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
