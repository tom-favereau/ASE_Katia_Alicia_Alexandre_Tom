package com.project.ase_project.unit.model.clean.summoner;

import com.project.ase_project.model.clean.summoner.Summoner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SummonerTests {
    static Summoner summoner;
    static Summoner summoner2;

    @BeforeAll
    public static void setUp() {
        summoner = new Summoner("Belugafurtif", 469, 132, 3.8f, 5, "", "1");
        summoner2 = new Summoner("Belugafurtif", 469, 132, 3.8f, 5, "", "1");
    }

    @Test
    public void testGettersAndSetters() {
        Summoner summonerTest = new Summoner();
        summonerTest.setName(summoner.getName());
        summonerTest.setProfileIconId(summoner.getProfileIconId());
        summonerTest.setSummonerLevel(summoner.getSummonerLevel());
        summonerTest.setAverage(summoner.getAverage());
        summonerTest.setCardinal(summoner.getCardinal());
        summonerTest.setPuuid(summoner.getPuuid());
        summonerTest.setId(summoner.getId());

        assertEquals(summoner.getName(), summonerTest.getName());
        assertEquals(summoner.getProfileIconId(), summonerTest.getProfileIconId());
        assertEquals(summoner.getSummonerLevel(), summonerTest.getSummonerLevel());
        assertEquals(summoner.getAverage(), summonerTest.getAverage());
        assertEquals(summoner.getCardinal(), summonerTest.getCardinal());
        assertEquals(summoner.getPuuid(), summonerTest.getPuuid());
        assertEquals(summoner.getId(), summonerTest.getId());
    }

    @Test
    public void testEqualsAndHashCode() {
        assertEquals(summoner, summoner2);
        assertEquals(summoner.hashCode(), summoner2.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "Summoner(name=Belugafurtif, profileIconId=469, summonerLevel=132, average=3.8, cardinal=5, puuid=, id=1)";
        assertEquals(expected, summoner.toString());
    }

    @Test
    public void testSummonerBuilder() {
        Summoner summonerTest = Summoner.builder()
                .name(summoner.getName())
                .profileIconId(summoner.getProfileIconId())
                .summonerLevel(summoner.getSummonerLevel())
                .average(summoner.getAverage())
                .cardinal(summoner.getCardinal())
                .puuid(summoner.getPuuid())
                .id(summoner.getId())
                .build();

        assertEquals(summoner.getName(), summonerTest.getName());
        assertEquals(summoner.getProfileIconId(), summonerTest.getProfileIconId());
        assertEquals(summoner.getSummonerLevel(), summonerTest.getSummonerLevel());
        assertEquals(summoner.getAverage(), summonerTest.getAverage());
        assertEquals(summoner.getCardinal(), summonerTest.getCardinal());
        assertEquals(summoner.getPuuid(), summonerTest.getPuuid());
        assertEquals(summoner.getId(), summonerTest.getId());
    }
}
