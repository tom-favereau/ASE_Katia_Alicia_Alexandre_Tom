package com.project.ase_project.unit.model.dto.summoner;

import com.project.ase_project.model.dto.summoner.SummonerDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SummonerDtoTests {
    static SummonerDto summonerDto;
    static SummonerDto summonerDto2;

    @BeforeAll
    public static void setUp() {
        summonerDto = new SummonerDto(
                "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ",
                "",
                "",
                "Belugafurtif",
                469,
                1654651320000L,
                132);
        summonerDto2 = new SummonerDto(
                "F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ",
                "",
                "",
                "Belugafurtif",
                469,
                1654651320000L,
                132);
    }

    @Test
    public void testGettersAndSetters() {
        SummonerDto summonerDtoTest = new SummonerDto();
        summonerDtoTest.setId(summonerDto.getId());
        summonerDtoTest.setAccountId(summonerDto.getAccountId());
        summonerDtoTest.setPuuid(summonerDto.getPuuid());
        summonerDtoTest.setName(summonerDto.getName());
        summonerDtoTest.setProfileIconId(summonerDto.getProfileIconId());
        summonerDtoTest.setRevisionDate(summonerDto.getRevisionDate());
        summonerDtoTest.setSummonerLevel(summonerDto.getSummonerLevel());

        assertEquals(summonerDto.getId(), summonerDtoTest.getId());
        assertEquals(summonerDto.getAccountId(), summonerDtoTest.getAccountId());
        assertEquals(summonerDto.getPuuid(), summonerDtoTest.getPuuid());
        assertEquals(summonerDto.getName(), summonerDtoTest.getName());
        assertEquals(summonerDto.getProfileIconId(), summonerDtoTest.getProfileIconId());
        assertEquals(summonerDto.getRevisionDate(), summonerDtoTest.getRevisionDate());
        assertEquals(summonerDto.getSummonerLevel(), summonerDtoTest.getSummonerLevel());
    }

    @Test
    public void testEqualsAndHashCode() {
        assertEquals(summonerDto, summonerDto2);
        assertEquals(summonerDto.hashCode(), summonerDto2.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "SummonerDto(id=F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ, accountId=, puuid=, name=Belugafurtif, profileIconId=469, revisionDate=1654651320000, summonerLevel=132)";
        assertEquals(expected, summonerDto.toString());
    }

    @Test
    public void testSummonerBuilder() {
        SummonerDto summonerDtoTest = SummonerDto.builder()
                .id(summonerDto.getId())
                .accountId(summonerDto.getAccountId())
                .puuid(summonerDto.getPuuid())
                .name(summonerDto.getName())
                .profileIconId(summonerDto.getProfileIconId())
                .revisionDate(summonerDto.getRevisionDate())
                .summonerLevel(summonerDto.getSummonerLevel())
                .build();

        assertEquals(summonerDto.getId(), summonerDtoTest.getId());
        assertEquals(summonerDto.getAccountId(), summonerDtoTest.getAccountId());
        assertEquals(summonerDto.getPuuid(), summonerDtoTest.getPuuid());
        assertEquals(summonerDto.getName(), summonerDtoTest.getName());
        assertEquals(summonerDto.getProfileIconId(), summonerDtoTest.getProfileIconId());
        assertEquals(summonerDto.getRevisionDate(), summonerDtoTest.getRevisionDate());
        assertEquals(summonerDto.getSummonerLevel(), summonerDtoTest.getSummonerLevel());
    }

    @Test
    public void testToSummoner() {
        assertEquals(summonerDto.toSummoner().getName(), summonerDto.getName());
        assertEquals(summonerDto.toSummoner().getProfileIconId(), summonerDto.getProfileIconId());
        assertEquals(summonerDto.toSummoner().getSummonerLevel(), summonerDto.getSummonerLevel());
        assertEquals(summonerDto.toSummoner().getId(), summonerDto.getId());
        assertEquals(summonerDto.toSummoner().getPuuid(), summonerDto.getPuuid());
    }
}
