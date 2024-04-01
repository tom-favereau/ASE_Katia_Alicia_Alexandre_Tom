package com.project.ase_project.unit.model.dto.match;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ase_project.model.dto.match.MatchDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatchDtoTests {
    static MatchDto matchDto;
    static MatchDto matchDto2;

    @BeforeAll
    public static void setUp() throws IOException, URISyntaxException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new URI("https://europe.api.riotgames.com/lol/match/v5/matches/EUW1_6760205418?api_key=RGAPI-7824e8d4-5ed0-4244-8b26-67ba3e260cc2").toURL());
        matchDto = objectMapper.readValue(jsonNode.toString(), MatchDto.class);
        matchDto2 = objectMapper.readValue(jsonNode.toString(), MatchDto.class);
    }

    @Test
    public void testGettersAndSetters() {
        MatchDto matchDtoTest = new MatchDto();
        matchDtoTest.setMetadata(matchDto.getMetadata());
        matchDtoTest.setInfo(matchDto.getInfo());

        assertEquals(matchDto.getMetadata(), matchDtoTest.getMetadata());
        assertEquals(matchDto.getInfo(), matchDtoTest.getInfo());
    }

    @Test
    public void testEqualsAndHashCode() {
        assertTrue(matchDto.equals(matchDto2) && matchDto2.equals(matchDto));
        assertEquals(matchDto.hashCode(), matchDto2.hashCode());
    }

    @Test
    public void testToString() {
        assertTrue(matchDto.toString().contains("metadata=" + matchDto.getMetadata().toString()));
        assertTrue(matchDto.toString().contains("info=" + matchDto.getInfo().toString()));
    }

    @Test
    public void testToMatch() {
        assertEquals(matchDto.toMatch(), matchDto2.toMatch());
    }
}
