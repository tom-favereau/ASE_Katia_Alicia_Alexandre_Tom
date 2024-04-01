package com.project.ase_project.cucumber;


import com.project.ase_project.model.clean.summary.Summary;
import com.project.ase_project.model.clean.summoner.Summoner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SummarySteps extends CucumberSpringConfiguration {
    private ResponseEntity<Summary> beluga;
    private ResponseEntity<Summary> razork;
    private ResponseEntity<String> errorNotFound;
    private ResponseEntity<String> errorBadRequest;

    ResponseEntity<Summary> getResponseEntity(String url){
        String fullUrl = "http://localhost:8080" + url;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(fullUrl, HttpMethod.GET, entity, Summary.class);
    }
    @When("the user calls {string}")
    public void theUserCalls(String url) {
        if (url.contains("Belugafurtif")){
            beluga = getResponseEntity(url);
        } else if (url.contains("Razørk Activoo")) {
            razork = getResponseEntity(url);
        }
    }

    @Then("the user receives {string}'s summary")
    public void theUserReceivesSSummary(String summoner) {
        if (summoner.equals("Belugafurtif")) {
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
        } else if (summoner.equals("Razørk Activoo")) {
            Summary belugaSummary = razork.getBody();
            assertEquals("Dno8J85K7DTwnqSlFm64Nk_681yWe3BFSWsDuLJ5ayxpRhA", belugaSummary.getSummonerId());
            assertEquals("Razørk Activoo", belugaSummary.getSummonerName());
            assertNotNull(belugaSummary.getSummonerLevel());
            assertNotNull(belugaSummary.getProfileIconId());
            assertNotNull(belugaSummary.getRankFlex());
            assertNotNull(belugaSummary.getRankSolo());
            assertNotNull(belugaSummary.getRegion());
            assertNotNull(belugaSummary.getAverage());
            assertNotNull(belugaSummary.getCardinal());
            assertNotNull(belugaSummary.getProfileIconAddress());
        }
    }

    @And("the response code status for {string} is {int}")
    public void theResponseCodeStatusForIs(String summoner, int code) {
        if (summoner.equals("Belugafurtif")) {
            assertEquals(code, beluga.getStatusCode().value());
        } else if (summoner.equals("Razørk Activoo")) {
            assertEquals(code, razork.getStatusCode().value());
        }
    }

    @When("a GET request is made to the endpoint {string} \\(where A does not exist)")
    public void aGETRequestIsMadeToTheEndpointWhereADoesNotExist(String url) {
        try {
            getResponseEntity(url);
        } catch (HttpClientErrorException ex) {
            errorNotFound = new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
        }
    }

    @Then("the API should respond with a status code {int} \\(Not Found)")
    public void theAPIShouldRespondWithAStatusCodeNotFound(int code) {
        assertEquals(code, errorNotFound.getStatusCode().value());
    }

    @And("the response body should contain an error message indicating that the summoner was not found")
    public void theResponseBodyShouldContainAnErrorMessageIndicatingThatTheSummonerWasNotFound() {
        assertEquals("Erreur 404 : Le joueur A n'existe pas.", errorNotFound.getBody());
    }

    @When("an invalid request is made to the endpoint {string} \\(where the summoner Name is missing)")
    public void anInvalidRequestIsMadeToTheEndpointWhereTheSummonerNameIsMissing(String url) {
        try {
            getResponseEntity(url);
        } catch (HttpClientErrorException ex) {
            errorBadRequest = new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
        }
    }

    @Then("the API should respond with a status code {int} \\(Bad Request)")
    public void theAPIShouldRespondWithAStatusCodeBadRequest(int code) {
        assertEquals(code, errorBadRequest.getStatusCode().value());
    }

    @And("the response body should contain an error message indicating that the request is invalid")
    public void theResponseBodyShouldContainAnErrorMessageIndicatingThatTheRequestIsInvalid() {
        assertEquals("Erreur 400 : Veuillez préciser un pseudo de joueur.", errorBadRequest.getBody());
    }
}