package com.project.ase_project.unit.model.clean.summary;

import com.project.ase_project.model.clean.league.League;
import com.project.ase_project.model.clean.summary.Summary;
import com.project.ase_project.model.clean.summoner.Summoner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SummaryTests {
    static Summary summary;
    static Summary summary2;
    static Summoner summoner;
    static ArrayList<League> leagues;
    @BeforeAll
    public static void setUp() {
        summoner = new Summoner("Belugafurtif", 469, 132, 3.8f, 5, "", "1");
        leagues = new ArrayList<>();
        leagues.add(new League("", "1", "Belugafurtif", "RANKED_SOLO_5x5", "Gold", "I", 100, 10, 5));
        leagues.add(new League("", "1", "Belugafurtif", "RANKED_FLEX_SR", "Silver", "I", 100, 10, 5));
        summary = new Summary(summoner, leagues);
        summary2 = new Summary(summoner, leagues);
    }

    @Test
    public void testGettersAndSetters() {
        Summary summaryTest = new Summary(new Summoner(), new ArrayList<>());
        summaryTest.setSummonerId(summary.getSummonerId());
        summaryTest.setSummonerName(summary.getSummonerName());
        summaryTest.setSummonerLevel(summary.getSummonerLevel());
        summaryTest.setProfileIconId(summary.getProfileIconId());
        summaryTest.setRankFlex(summary.getRankFlex());
        summaryTest.setRankSolo(summary.getRankSolo());
        summaryTest.setRegion(summary.getRegion());
        summaryTest.setAverage(summary.getAverage());
        summaryTest.setCardinal(summary.getCardinal());

        assertEquals(summary.getSummonerId(), summaryTest.getSummonerId());
        assertEquals(summary.getSummonerName(), summaryTest.getSummonerName());
        assertEquals(summary.getSummonerLevel(), summaryTest.getSummonerLevel());
        assertEquals(summary.getProfileIconId(), summaryTest.getProfileIconId());
        assertEquals(summary.getRankFlex(), summaryTest.getRankFlex());
        assertEquals(summary.getRankSolo(), summaryTest.getRankSolo());
        assertEquals(summary.getRegion(), summaryTest.getRegion());
        assertEquals(summary.getAverage(), summaryTest.getAverage());
        assertEquals(summary.getCardinal(), summaryTest.getCardinal());
    }

    @Test
    public void testEqualsAndHashCode() {
        assertTrue(summary.equals(summary2) && summary2.equals(summary));
        assertEquals(summary.hashCode(), summary2.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "Summary(" +
                "summonerId=1, " +
                "summonerName=Belugafurtif, " +
                "summonerLevel=132, " +
                "profileIconId=469, " +
                "rankFlex=Silver I 100 LP 10W / 5L, " +
                "rankSolo=Gold I 100 LP 10W / 5L, " +
                "region=EUW1, " +
                "average=3.8, " +
                "cardinal=5, " +
                "profileIconAddress=https://ddragon.leagueoflegends.com/cdn/14.5.1/img/profileicon/" + summoner.getProfileIconId() + ".png)";
        assertEquals(expected, summary.toString());
    }
}
