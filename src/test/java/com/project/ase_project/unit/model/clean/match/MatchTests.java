package com.project.ase_project.unit.model.clean.match;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ase_project.model.clean.match.*;
import com.project.ase_project.model.dto.match.MatchDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MatchTests {
    static MatchDto matchDto;
    static Match match;
    static Match match2;
    @BeforeAll
    public static void setup() throws IOException, URISyntaxException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new URI("https://europe.api.riotgames.com/lol/match/v5/matches/EUW1_6760205418?api_key=RGAPI-7824e8d4-5ed0-4244-8b26-67ba3e260cc2").toURL());
        matchDto = objectMapper.readValue(jsonNode.toString(), MatchDto.class);
        match = matchDto.toMatch();
        match2 = matchDto.toMatch();
    }
    @Test
    public void testGettersAndSetters() {
        Match matchTest = new Match();
        matchTest.setMatchId(matchDto.getMetadata().getMatchId());
        matchTest.setMetadata(
                new Metadata(
                        matchDto.getInfo().getGameCreation(),
                        matchDto.getInfo().getGameDuration(),
                        matchDto.getInfo().getGameEndTimestamp(),
                        matchDto.getMetadata().getDataVersion(),
                        matchDto.getInfo().getGameId(),
                        matchDto.getInfo().getPlatformId(),
                        matchDto.getInfo().getGameMode(),
                        matchDto.getInfo().getGameName(),
                        matchDto.getInfo().getGameType(),
                        matchDto.getInfo().getGameVersion(),
                        matchDto.getInfo().getMapId(),
                        matchDto.getInfo().getQueueId()
        ));
        List<Participant> participants = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            participants.add(new Participant(
                    matchDto.getInfo().getParticipants().get(i).getPuuid(),
                    matchDto.getInfo().getParticipants().get(i).getSummonerId(),
                    matchDto.getInfo().getParticipants().get(i).getSummonerName(),
                    matchDto.getInfo().getParticipants().get(i).getSummonerLevel(),
                    matchDto.getInfo().getParticipants().get(i).getParticipantId(),
                    matchDto.getInfo().getParticipants().get(i).getRiotIdGameName(),
                    matchDto.getInfo().getParticipants().get(i).getRiotIdTagline(),
                    matchDto.getInfo().getParticipants().get(i).getTeamId(),
                    matchDto.getInfo().getParticipants().get(i).getRole(),
                    matchDto.getInfo().getParticipants().get(i).getLane(),
                    matchDto.getInfo().getParticipants().get(i).getTeamPosition(),
                    matchDto.getInfo().getParticipants().get(i).getIndividualPosition(),
                    matchDto.getInfo().getParticipants().get(i).getChampionId(),
                    matchDto.getInfo().getParticipants().get(i).getChampionName(),
                    matchDto.getInfo().getParticipants().get(i).isWin(),
                    matchDto.getInfo().getParticipants().get(i).getKills(),
                    matchDto.getInfo().getParticipants().get(i).getDeaths(),
                    matchDto.getInfo().getParticipants().get(i).getAssists(),
                    matchDto.getInfo().getParticipants().get(i).getVisionScore()
            ));
        }
        matchTest.setParticipants(participants);
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            teams.add(new Team(
                    matchDto.getInfo().getTeams().get(i).getTeamId(),
                    matchDto.getInfo().getTeams().get(i).isWin(),
                    matchDto.getInfo().getTeams().get(i).getObjectives().getBaron().isFirst(),
                    matchDto.getInfo().getTeams().get(i).getObjectives().getBaron().getKills(),
                    matchDto.getInfo().getTeams().get(i).getObjectives().getChampion().isFirst(),
                    matchDto.getInfo().getTeams().get(i).getObjectives().getChampion().getKills(),
                    matchDto.getInfo().getTeams().get(i).getObjectives().getDragon().isFirst(),
                    matchDto.getInfo().getTeams().get(i).getObjectives().getDragon().getKills(),
                    matchDto.getInfo().getTeams().get(i).getObjectives().getHorde().isFirst(),
                    matchDto.getInfo().getTeams().get(i).getObjectives().getHorde().getKills(),
                    matchDto.getInfo().getTeams().get(i).getObjectives().getInhibitor().isFirst(),
                    matchDto.getInfo().getTeams().get(i).getObjectives().getInhibitor().getKills(),
                    matchDto.getInfo().getTeams().get(i).getObjectives().getRiftHerald().isFirst(),
                    matchDto.getInfo().getTeams().get(i).getObjectives().getRiftHerald().getKills(),
                    matchDto.getInfo().getTeams().get(i).getObjectives().getTower().isFirst(),
                    matchDto.getInfo().getTeams().get(i).getObjectives().getTower().getKills()
            ));
        }
        matchTest.setTeams(teams);
        List<Draft> drafts = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            drafts.add(new Draft(
                    matchDto.getInfo().getTeams().get(i).getTeamId(),
                    matchDto.getInfo().getTeams().get(i).getBans().get(0).getChampionId(),
                    matchDto.getInfo().getTeams().get(i).getBans().get(1).getChampionId(),
                    matchDto.getInfo().getTeams().get(i).getBans().get(2).getChampionId(),
                    matchDto.getInfo().getTeams().get(i).getBans().get(3).getChampionId(),
                    matchDto.getInfo().getTeams().get(i).getBans().get(4).getChampionId(),
                    matchDto.getInfo().getParticipants().get(5*i).getChampionId(),
                    matchDto.getInfo().getParticipants().get(5*i+1).getChampionId(),
                    matchDto.getInfo().getParticipants().get(5*i+2).getChampionId(),
                    matchDto.getInfo().getParticipants().get(5*i+3).getChampionId(),
                    matchDto.getInfo().getParticipants().get(5*i+4).getChampionId()
            ));
        }
        matchTest.setDrafts(drafts);

        assertEquals(match.getMatchId(), matchTest.getMatchId());
        assertEquals(match.getMetadata(), matchTest.getMetadata());
        assertEquals(match.getParticipants(), matchTest.getParticipants());
        assertEquals(match.getTeams(), matchTest.getTeams());
        assertEquals(match.getDrafts(), matchTest.getDrafts());
    }

    @Test
    public void testEqualsAndHashCode() {
        assertTrue(match.equals(match2) && match2.equals(match));
        assertEquals(match.hashCode(), match2.hashCode());
    }

    @Test
    public void testToString() {
        String expected =
                "Match(matchId=EUW1_6760205418, " +
                "metadata=Metadata(" +
                        "gameCreation=1704900909290, " +
                        "gameDuration=2171, " +
                        "gameEndTimestamp=1704903099430, " +
                        "dataVersion=2, " +
                        "gameId=6760205418, " +
                        "platformId=EUW1, " +
                        "gameMode=CLASSIC, " +
                        "gameName=teambuilder-match-6760205418, " +
                        "gameType=MATCHED_GAME, " +
                        "gameVersion=14.1.552.7117, " +
                        "mapId=11, queueId=400), " +
                        "participants=[" +
                                "Participant(" +
                                        "puuid=NSdcoRFNf4QK4MXv3t7k5DLJg3DBE6YpuGd2wIgmD57B3hYosKCeSePNoftPMcqRoXYqCnLycHTQvg, " +
                                        "summonerId=Cnv9sKyA1mkOHb_cEX23LW0f9wIeceofEC5-iu5SIt9OJ1UPH5Gn7EAYsg, " +
                                        "summonerName=020911 080623, " +
                                        "summonerLevel=89, " +
                                        "participantId=1, " +
                                        "riotIdGameName=Forloven, " +
                                        "riotIdTagLine=Link, " +
                                        "teamId=100, " +
                                        "role=SOLO, " +
                                        "lane=TOP, " +
                                        "teamPosition=TOP, " +
                                        "individualPosition=TOP, " +
                                        "championId=23, " +
                                        "championName=Tryndamere, " +
                                        "win=false, " +
                                        "kills=5, " +
                                        "deaths=9, " +
                                        "assists=1, " +
                                        "visionScore=24), " +
                                "Participant(" +
                                        "puuid=W-6lu3K-72edepuy0jCAwrGtYt_8wCdKavdtq4p_6Hi6XZDeGAWoOns5WJ06_IJcY4qIam0EEvn2WA, " +
                                        "summonerId=sCRgRqOCVUPBqHa8NPneL-8pjG-jdPURmBsrmXa68rAASAhGyg4vD6MAxw, " +
                                        "summonerName=, " +
                                        "summonerLevel=51, " +
                                        "participantId=2, " +
                                        "riotIdGameName=Joggeson, " +
                                        "riotIdTagLine=4711, " +
                                        "teamId=100, " +
                                        "role=NONE, " +
                                        "lane=JUNGLE, " +
                                        "teamPosition=JUNGLE, " +
                                        "individualPosition=JUNGLE, " +
                                        "championId=120, " +
                                        "championName=Hecarim, " +
                                        "win=false, " +
                                        "kills=6, " +
                                        "deaths=6, " +
                                        "assists=7, " +
                                        "visionScore=14), " +
                                "Participant(" +
                                        "puuid=VnbzIL6pFXWJxOoMxeaAJ4yOgigM0YC3VkY9KlGz2TcdFf67mVUf9Kg3JA42PHbzTrseflM_yu-FcQ, " +
                                        "summonerId=J-ufPHbuxRnhq-t-RQ2aVzEb-yT0cANM0LmkqfomUf58ZLE, " +
                                        "summonerName=Takumi D, " +
                                        "summonerLevel=534, " +
                                        "participantId=3, " +
                                        "riotIdGameName=Takumi D, " +
                                        "riotIdTagLine=0000, " +
                                        "teamId=100, " +
                                        "role=SOLO, " +
                                        "lane=MIDDLE, " +
                                        "teamPosition=MIDDLE, " +
                                        "individualPosition=MIDDLE, " +
                                        "championId=3, " +
                                        "championName=Galio, " +
                                        "win=false, " +
                                        "kills=12, " +
                                        "deaths=6, " +
                                        "assists=15, " +
                                        "visionScore=19), " +
                                "Participant(" +
                                        "puuid=RPYtYTe4r08QikX2qNCALO5q3n9NU3d6FWeaNparmHEuCgcPaLtX-ag_uuM9lWBt2hLoU14OqcDJqA, " +
                                        "summonerId=RLzpVRRhKRkOE5ehG7eY2u9QJl6dVNXu8U8vNeWK2nOmCIlG, " +
                                        "summonerName=49 3 enjoyers, " +
                                        "summonerLevel=280, " +
                                        "participantId=4, " +
                                        "riotIdGameName=49 3 enjoyers, " +
                                        "riotIdTagLine=EUW, " +
                                        "teamId=100, " +
                                        "role=CARRY, " +
                                        "lane=BOTTOM, " +
                                        "teamPosition=BOTTOM, " +
                                        "individualPosition=BOTTOM, " +
                                        "championId=96, " +
                                        "championName=KogMaw, " +
                                        "win=false, " +
                                        "kills=16, " +
                                        "deaths=6, " +
                                        "assists=7, " +
                                        "visionScore=17), " +
                                "Participant(" +
                                        "puuid=1MXlsiEgwnRYgQf3VmSvbxmEYZm3H_8-FGwa1he4WrWL_Uew-19s-gjqIX95yDcwbOR5gTvhFi7riw, " +
                                        "summonerId=R2zJW-tMOSeILg5PbgRqS9bOdlhgAwli-JLUN1T5c40WATg, " +
                                        "summonerName=DOLLEX ENJOYER, " +
                                        "summonerLevel=556, " +
                                        "participantId=5, " +
                                        "riotIdGameName=DOLLEX ENJOYER, " +
                                        "riotIdTagLine=EUW, " +
                                        "teamId=100, " +
                                        "role=SUPPORT, " +
                                        "lane=BOTTOM, " +
                                        "teamPosition=UTILITY, " +
                                        "individualPosition=UTILITY, " +
                                        "championId=497, " +
                                        "championName=Rakan, " +
                                        "win=false, " +
                                        "kills=1, " +
                                        "deaths=11, " +
                                        "assists=17, " +
                                        "visionScore=84), " +
                                "Participant(" +
                                        "puuid=dxoUWrKBlQB9xP7tDH3nR4yF8FwOpBnMgXP-GIE6n80nHNuaw8ZTVDJxX8Xdw7ZE8d6c-9MnifVJrw, " +
                                        "summonerId=fzni3JgU1zkhM2CsetTCLDRMVOsMm-Z6mx2mMgSwdFvvFZk, " +
                                        "summonerName=inugami korone, " +
                                        "summonerLevel=564, " +
                                        "participantId=6, " +
                                        "riotIdGameName=hatsune miku, " +
                                        "riotIdTagLine=999, " +
                                        "teamId=200, " +
                                        "role=SOLO, " +
                                        "lane=TOP, " +
                                        "teamPosition=TOP, " +
                                        "individualPosition=TOP," +
                                        " championId=516, " +
                                        "championName=Ornn, " +
                                        "win=true, " +
                                        "kills=1, " +
                                        "deaths=4, " +
                                        "assists=15, " +
                                        "visionScore=20), " +
                                "Participant(" +
                                        "puuid=n0eI8uzwTTAg6dQgpQTQN-LLfdU3_SKHszG0sGs1UiJx1l611YAjlQGLr6HENdlC1ZZYQ9ZiTfGUzw, " +
                                        "summonerId=aSaFsPEmtoVFz6SQzGIjsGoZHn4Otw67YxDWTE8bA84W-Wgl, " +
                                        "summonerName=Not Simon, " +
                                        "summonerLevel=341, " +
                                        "participantId=7, " +
                                        "riotIdGameName=Not Simon, " +
                                        "riotIdTagLine=EUW, " +
                                        "teamId=200, " +
                                        "role=NONE, " +
                                        "lane=JUNGLE, " +
                                        "teamPosition=JUNGLE, " +
                                        "individualPosition=JUNGLE, " +
                                        "championId=63, " +
                                        "championName=Brand, " +
                                        "win=true, " +
                                        "kills=7, " +
                                        "deaths=10, " +
                                        "assists=8, " +
                                        "visionScore=40), " +
                                "Participant(" +
                                        "puuid=6sHKShM4yhPnIMrhUHQjuWVk9gJFJmlgEguUYma7NQxX7KP0RSmsUWY13hq6IKaDa6P12x-2DV4p4w, " +
                                        "summonerId=6mIria3VyGwD6uwjO_CmhcGSXqn5jpcc41KkaJ41-aHZDnFlRZasU5pGUQ, " +
                                        "summonerName=aPoly, " +
                                        "summonerLevel=408, " +
                                        "participantId=8, " +
                                        "riotIdGameName=Poli, " +
                                        "riotIdTagLine=2469, " +
                                        "teamId=200, " +
                                        "role=SOLO, " +
                                        "lane=MIDDLE, " +
                                        "teamPosition=MIDDLE, " +
                                        "individualPosition=MIDDLE, " +
                                        "championId=126, " +
                                        "championName=Jayce, " +
                                        "win=true, " +
                                        "kills=9, " +
                                        "deaths=12, " +
                                        "assists=8, " +
                                        "visionScore=17), " +
                                "Participant(" +
                                        "puuid=Gw-DlJXWkCliGKvzx7SxAltlyLYR3pSMN7qNmDFtE3qK9a1V3wXwltB8gxsb985Bw4WQtrefPYc6Cg, " +
                                        "summonerId=-qzmNly6ihP4GwUnssjThDcodgKufnQNHvlvPO3hBTx9tyE, " +
                                        "summonerName=Heyxler, " +
                                        "summonerLevel=835, " +
                                        "participantId=9, " +
                                        "riotIdGameName=Heyxler, " +
                                        "riotIdTagLine=187, " +
                                        "teamId=200, " +
                                        "role=CARRY, " +
                                        "lane=BOTTOM, " +
                                        "teamPosition=BOTTOM, " +
                                        "individualPosition=BOTTOM, " +
                                        "championId=51, " +
                                        "championName=Caitlyn, " +
                                        "win=true, " +
                                        "kills=10, " +
                                        "deaths=7, " +
                                        "assists=12, " +
                                        "visionScore=37), " +
                                "Participant(" +
                                        "puuid=BUJmPYBwQjeaT-QBwg5v5FPdJh3rcoJaY7rjqq1PBuVNztGApND7ixjvwvg7d7GKvLUNtWoX3LDsbw, " +
                                        "summonerId=ncNTafaHc1l5SDpWl3HFsTtT1PsSJvSQQvxjYkkEEc2Qst8, " +
                                        "summonerName=Epic1ndi, " +
                                        "summonerLevel=26, " +
                                        "participantId=10, " +
                                        "riotIdGameName=Epic1ndi, " +
                                        "riotIdTagLine=EUW, " +
                                        "teamId=200, " +
                                        "role=SUPPORT, " +
                                        "lane=BOTTOM, " +
                                        "teamPosition=UTILITY, " +
                                        "individualPosition=UTILITY, " +
                                        "championId=99, " +
                                        "championName=Lux, " +
                                        "win=true, " +
                                        "kills=11, " +
                                        "deaths=7, " +
                                        "assists=12, " +
                                        "visionScore=69)], " +
                        "teams=[" +
                                "Team(" +
                                        "teamId=100, " +
                                        "win=false, " +
                                        "firstBaron=true, " +
                                        "baronKills=1, " +
                                        "firstChampion=true, " +
                                        "championKills=40, " +
                                        "firstDragon=true, " +
                                        "dragonKills=2, " +
                                        "firstHorde=true, " +
                                        "hordeKills=4, " +
                                        "firstInhibitor=true, " +
                                        "inhibitorKills=1, " +
                                        "firstRiftHerald=true, " +
                                        "riftHeraldKills=1, " +
                                        "firstTower=false, " +
                                        "towerKills=8), " +
                                "Team(" +
                                        "teamId=200, " +
                                        "win=true, " +
                                        "firstBaron=false, " +
                                        "baronKills=0, " +
                                        "firstChampion=false, " +
                                        "championKills=38, " +
                                        "firstDragon=false, " +
                                        "dragonKills=2, " +
                                        "firstHorde=false, " +
                                        "hordeKills=2, " +
                                        "firstInhibitor=false, " +
                                        "inhibitorKills=1, " +
                                        "firstRiftHerald=false, " +
                                        "riftHeraldKills=0, " +
                                        "firstTower=true, " +
                                        "towerKills=9)], " +
                        "drafts=[" +
                                "Draft(" +
                                        "teamId=100, " +
                                        "firstBan=360, " +
                                        "secondBan=121, " +
                                        "thirdBan=53, " +
                                        "fourthBan=55, " +
                                        "fifthBan=24, " +
                                        "firstPick=23, " +
                                        "secondPick=120, " +
                                        "thirdPick=3, " +
                                        "fourthPick=96, " +
                                        "fifthPick=497), " +
                                "Draft(" +
                                        "teamId=200, " +
                                        "firstBan=107, " +
                                        "secondBan=84, " +
                                        "thirdBan=-1, " +
                                        "fourthBan=81, " +
                                        "fifthBan=48, " +
                                        "firstPick=516, " +
                                        "secondPick=63, " +
                                        "thirdPick=126, " +
                                        "fourthPick=51, " +
                                        "fifthPick=99)])";
        assertEquals(expected, match.toString());
    }

    @Test
    public void testMatchBuilder() {
        String matchId = "testMatchId";
        Metadata metadata = new Metadata();
        List<Participant> participants = new ArrayList<>();
        List<Team> teams = new ArrayList<>();
        List<Draft> drafts = new ArrayList<>();

        Match match = Match.builder()
                .matchId(matchId)
                .metadata(metadata)
                .participants(participants)
                .teams(teams)
                .drafts(drafts)
                .build();

        assertNotNull(match);
        assertEquals(matchId, match.getMatchId());
        assertEquals(metadata, match.getMetadata());
        assertEquals(participants, match.getParticipants());
        assertEquals(teams, match.getTeams());
        assertEquals(drafts, match.getDrafts());
    }

    @Test
    public void testMetadataBuilder() {
        Metadata metadata = Metadata.builder()
                .gameCreation(matchDto.getInfo().getGameCreation())
                .gameDuration(matchDto.getInfo().getGameDuration())
                .gameEndTimestamp(matchDto.getInfo().getGameEndTimestamp())
                .dataVersion(matchDto.getMetadata().getDataVersion())
                .gameId(matchDto.getInfo().getGameId())
                .platformId(matchDto.getInfo().getPlatformId())
                .gameMode(matchDto.getInfo().getGameMode())
                .gameName(matchDto.getInfo().getGameName())
                .gameType(matchDto.getInfo().getGameType())
                .gameVersion(matchDto.getInfo().getGameVersion())
                .mapId(matchDto.getInfo().getMapId())
                .queueId(matchDto.getInfo().getQueueId())
                .build();

        assertNotNull(metadata);
        assertEquals(metadata.getGameCreation(), matchDto.getInfo().getGameCreation());
        assertEquals(metadata.getGameDuration(), matchDto.getInfo().getGameDuration());
        assertEquals(metadata.getGameEndTimestamp(), matchDto.getInfo().getGameEndTimestamp());
        assertEquals(metadata.getDataVersion(), matchDto.getMetadata().getDataVersion());
        assertEquals(metadata.getGameId(), matchDto.getInfo().getGameId());
        assertEquals(metadata.getPlatformId(), matchDto.getInfo().getPlatformId());
        assertEquals(metadata.getGameMode(), matchDto.getInfo().getGameMode());
        assertEquals(metadata.getGameName(), matchDto.getInfo().getGameName());
        assertEquals(metadata.getGameType(), matchDto.getInfo().getGameType());
        assertEquals(metadata.getGameVersion(), matchDto.getInfo().getGameVersion());
        assertEquals(metadata.getMapId(), matchDto.getInfo().getMapId());
        assertEquals(metadata.getQueueId(), matchDto.getInfo().getQueueId());
    }

    @Test
    public void testParticipantBuilder() {
        Participant participant = Participant.builder()
                .puuid("testPuuid")
                .summonerId("testSummonerId")
                .summonerName("testSummonerName")
                .summonerLevel(1)
                .participantId(1)
                .riotIdGameName("testRiotIdGameName")
                .riotIdTagLine("testRiotIdTagline")
                .teamId(100)
                .role("testRole")
                .lane("testLane")
                .teamPosition("testTeamPosition")
                .individualPosition("testIndividualPosition")
                .championId(1)
                .championName("testChampionName")
                .win(true)
                .kills(1)
                .deaths(1)
                .assists(1)
                .visionScore(1)
                .build();

        assertNotNull(participant);
        assertEquals("testPuuid", participant.getPuuid());
        assertEquals("testSummonerId", participant.getSummonerId());
        assertEquals("testSummonerName", participant.getSummonerName());
        assertEquals(1, participant.getSummonerLevel());
        assertEquals(1, participant.getParticipantId());
        assertEquals("testRiotIdGameName", participant.getRiotIdGameName());
        assertEquals("testRiotIdTagline", participant.getRiotIdTagLine());
        assertEquals(100, participant.getTeamId());
        assertEquals("testRole", participant.getRole());
        assertEquals("testLane", participant.getLane());
        assertEquals("testTeamPosition", participant.getTeamPosition());
        assertEquals("testIndividualPosition", participant.getIndividualPosition());
        assertEquals(1, participant.getChampionId());
        assertEquals("testChampionName", participant.getChampionName());
        assertTrue(participant.isWin());
        assertEquals(1, participant.getKills());
        assertEquals(1, participant.getDeaths());
        assertEquals(1, participant.getAssists());
        assertEquals(1, participant.getVisionScore());
    }

    @Test
    public void testTeamBuilder() {
        Team team = Team.builder()
                .teamId(100)
                .win(true)
                .firstBaron(true)
                .baronKills(1)
                .firstChampion(true)
                .championKills(1)
                .firstDragon(true)
                .dragonKills(1)
                .firstHorde(true)
                .hordeKills(1)
                .firstInhibitor(true)
                .inhibitorKills(1)
                .firstRiftHerald(true)
                .riftHeraldKills(1)
                .firstTower(true)
                .towerKills(1)
                .build();

        assertNotNull(team);
        assertEquals(100, team.getTeamId());
        assertTrue(team.isWin());
        assertTrue(team.isFirstBaron());
        assertEquals(1, team.getBaronKills());
        assertTrue(team.isFirstChampion());
        assertEquals(1, team.getChampionKills());
        assertTrue(team.isFirstDragon());
        assertEquals(1, team.getDragonKills());
        assertTrue(team.isFirstHorde());
        assertEquals(1, team.getHordeKills());
        assertTrue(team.isFirstInhibitor());
        assertEquals(1, team.getInhibitorKills());
        assertTrue(team.isFirstRiftHerald());
        assertEquals(1, team.getRiftHeraldKills());
        assertTrue(team.isFirstTower());
        assertEquals(1, team.getTowerKills());
    }

    @Test
    public void testDraftBuilder() {
        Draft draft = Draft.builder()
                .teamId(100)
                .firstBan(1)
                .secondBan(2)
                .thirdBan(3)
                .fourthBan(4)
                .fifthBan(5)
                .firstPick(6)
                .secondPick(7)
                .thirdPick(8)
                .fourthPick(9)
                .fifthPick(10)
                .build();

        assertNotNull(draft);
        assertEquals(100, draft.getTeamId());
        assertEquals(1, draft.getFirstBan());
        assertEquals(2, draft.getSecondBan());
        assertEquals(3, draft.getThirdBan());
        assertEquals(4, draft.getFourthBan());
        assertEquals(5, draft.getFifthBan());
        assertEquals(6, draft.getFirstPick());
        assertEquals(7, draft.getSecondPick());
        assertEquals(8, draft.getThirdPick());
        assertEquals(9, draft.getFourthPick());
        assertEquals(10, draft.getFifthPick());
    }
}