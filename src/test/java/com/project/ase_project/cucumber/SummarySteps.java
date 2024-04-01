package com.project.ase_project.cucumber;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ase_project.AseProjectApplication;
import com.project.ase_project.model.clean.summary.Summary;
import com.project.ase_project.model.ddragon.maps.LOLMap;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SummarySteps extends CucumberSpringConfiguration {
    private ResponseEntity<Summary> beluga;
    @When("^the user calls /riot/summary/Belugafurtif$")
    public void the_user_issues_GET_summary() {
        String url = "http://localhost:8080/riot/summary/Belugafurtif";
        //belugaSummary = new RestTemplate().getForObject(url, Summary.class);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        beluga = restTemplate.exchange(url, HttpMethod.GET, entity, Summary.class);
    }

    @Then("the user receives summoner summary")
    public void theUserReceivesSummonerSummary() {
        Summary belugaSummary = beluga.getBody();
        assertEquals("F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ", belugaSummary.getSummonerId());
        assertEquals("Belugafurtif", belugaSummary.getSummonerName());
        assertNotNull(belugaSummary.getSummonerLevel());
        assertNotNull(belugaSummary.getProfileIconId());
        assertNotNull(belugaSummary.getRankFlex());
        assertNotNull(belugaSummary.getRankSolo());
        assertNotNull(belugaSummary.getRegion());
        assertNotNull(belugaSummary.getAverage());
        assertNotNull(belugaSummary.getCardinal());
        assertNotNull(belugaSummary.getProfileIconAddress());
    }

    @And("the response code status is {int}")
    public void theResponseCodeStatusIs(int arg0) {
        assertEquals(arg0, beluga.getStatusCode().value());
    }
}