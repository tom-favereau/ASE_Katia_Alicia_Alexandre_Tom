package com.project.ase_project.unit.model.clean.league;

import com.project.ase_project.model.clean.league.League;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeagueTests {
    static League league;
    static League league2;

    @BeforeAll
    public static void setUp() {
        league = new League("1", "2", "Test", "Solo", "Gold", "I", 100, 10, 5);
        league2 = new League("1", "2", "Test", "Solo", "Gold", "I", 100, 10, 5);
    }

    @Test
    public void testGettersAndSetters() {
        League leagueTest = new League();
        leagueTest.setLeagueId(league.getLeagueId());
        leagueTest.setSummonerId(league.getSummonerId());
        leagueTest.setSummonerName(league.getSummonerName());
        leagueTest.setQueueType(league.getQueueType());
        leagueTest.setTier(league.getTier());
        leagueTest.setRank(league.getRank());
        leagueTest.setLeaguePoints(league.getLeaguePoints());
        leagueTest.setWins(league.getWins());
        leagueTest.setLosses(league.getLosses());

        assertEquals(league.getLeagueId(), leagueTest.getLeagueId());
        assertEquals(league.getSummonerId(), leagueTest.getSummonerId());
        assertEquals(league.getSummonerName(), leagueTest.getSummonerName());
        assertEquals(league.getQueueType(), leagueTest.getQueueType());
        assertEquals(league.getTier(), leagueTest.getTier());
        assertEquals(league.getRank(), leagueTest.getRank());
        assertEquals(league.getLeaguePoints(), leagueTest.getLeaguePoints());
        assertEquals(league.getWins(), leagueTest.getWins());
        assertEquals(league.getLosses(), leagueTest.getLosses());
    }

    @Test
    public void testEqualsAndHashCode() {
        assertTrue(league.equals(league2) && league2.equals(league));
        assertEquals(league.hashCode(), league2.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "League(leagueId=1, summonerId=2, summonerName=Test, queueType=Solo, tier=Gold, rank=I, leaguePoints=100, wins=10, losses=5)";
        assertEquals(expected, league.toString());
    }

    @Test
    public void testBuilder() {
        League leagueTest = League.builder()
                .leagueId(league.getLeagueId())
                .summonerId(league.getSummonerId())
                .summonerName(league.getSummonerName())
                .queueType(league.getQueueType())
                .tier(league.getTier())
                .rank(league.getRank())
                .leaguePoints(league.getLeaguePoints())
                .wins(league.getWins())
                .losses(league.getLosses())
                .build();

        assertEquals(league.getLeagueId(), leagueTest.getLeagueId());
        assertEquals(league.getSummonerId(), leagueTest.getSummonerId());
        assertEquals(league.getSummonerName(), leagueTest.getSummonerName());
        assertEquals(league.getQueueType(), leagueTest.getQueueType());
        assertEquals(league.getTier(), leagueTest.getTier());
        assertEquals(league.getRank(), leagueTest.getRank());
        assertEquals(league.getLeaguePoints(), leagueTest.getLeaguePoints());
        assertEquals(league.getWins(), leagueTest.getWins());
        assertEquals(league.getLosses(), leagueTest.getLosses());
    }
}
