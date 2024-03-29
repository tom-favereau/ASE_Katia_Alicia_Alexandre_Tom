package com.project.ase_project.unit.model.dto.league;

import com.project.ase_project.model.dto.league.LeagueDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeagueDtoTests {
    static LeagueDto leagueDto;
    static LeagueDto leagueDto2;

    @BeforeAll
    public static void setUp() {
        leagueDto = new LeagueDto("1", "RANKED_FLEX_SR", "GOLD", "III", "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ", "Belugafurtif", 100, 10, 5, false, false, false, false);
        leagueDto2 = new LeagueDto("1", "RANKED_FLEX_SR", "GOLD", "III", "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ", "Belugafurtif", 100, 10, 5, false, false, false, false);
    }

    @Test
    public void testGettersAndSetters() {
        LeagueDto leagueDtoTest = new LeagueDto();
        leagueDtoTest.setLeagueId(leagueDto.getLeagueId());
        leagueDtoTest.setSummonerId(leagueDto.getSummonerId());
        leagueDtoTest.setSummonerName(leagueDto.getSummonerName());
        leagueDtoTest.setQueueType(leagueDto.getQueueType());
        leagueDtoTest.setTier(leagueDto.getTier());
        leagueDtoTest.setRank(leagueDto.getRank());
        leagueDtoTest.setLeaguePoints(leagueDto.getLeaguePoints());
        leagueDtoTest.setWins(leagueDto.getWins());
        leagueDtoTest.setLosses(leagueDto.getLosses());
        leagueDtoTest.setVeteran(leagueDto.isVeteran());
        leagueDtoTest.setInactive(leagueDto.isInactive());
        leagueDtoTest.setFreshBlood(leagueDto.isFreshBlood());
        leagueDtoTest.setHotStreak(leagueDto.isHotStreak());

        assertEquals(leagueDto.getLeagueId(), leagueDtoTest.getLeagueId());
        assertEquals(leagueDto.getSummonerId(), leagueDtoTest.getSummonerId());
        assertEquals(leagueDto.getSummonerName(), leagueDtoTest.getSummonerName());
        assertEquals(leagueDto.getQueueType(), leagueDtoTest.getQueueType());
        assertEquals(leagueDto.getTier(), leagueDtoTest.getTier());
        assertEquals(leagueDto.getRank(), leagueDtoTest.getRank());
        assertEquals(leagueDto.getLeaguePoints(), leagueDtoTest.getLeaguePoints());
        assertEquals(leagueDto.getWins(), leagueDtoTest.getWins());
        assertEquals(leagueDto.getLosses(), leagueDtoTest.getLosses());
        assertEquals(leagueDto.isVeteran(), leagueDtoTest.isVeteran());
        assertEquals(leagueDto.isInactive(), leagueDtoTest.isInactive());
        assertEquals(leagueDto.isFreshBlood(), leagueDtoTest.isFreshBlood());
        assertEquals(leagueDto.isHotStreak(), leagueDtoTest.isHotStreak());
    }

    @Test
    public void testEqualsAndHashCode() {
        assertEquals(leagueDto, leagueDto2);
        assertEquals(leagueDto.hashCode(), leagueDto2.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "LeagueDto(leagueId=1, queueType=RANKED_FLEX_SR, tier=GOLD, rank=III, summonerId=F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ, summonerName=Belugafurtif, leaguePoints=100, wins=10, losses=5, veteran=false, inactive=false, freshBlood=false, hotStreak=false)";
        assertEquals(expected, leagueDto.toString());
    }

    @Test
    public void testBuilder() {
        LeagueDto leagueDtoTest = LeagueDto.builder()
                .leagueId(leagueDto.getLeagueId())
                .summonerId(leagueDto.getSummonerId())
                .summonerName(leagueDto.getSummonerName())
                .queueType(leagueDto.getQueueType())
                .tier(leagueDto.getTier())
                .rank(leagueDto.getRank())
                .leaguePoints(leagueDto.getLeaguePoints())
                .wins(leagueDto.getWins())
                .losses(leagueDto.getLosses())
                .veteran(leagueDto.isVeteran())
                .inactive(leagueDto.isInactive())
                .freshBlood(leagueDto.isFreshBlood())
                .hotStreak(leagueDto.isHotStreak())
                .build();

        assertEquals(leagueDto, leagueDtoTest);
    }
}
