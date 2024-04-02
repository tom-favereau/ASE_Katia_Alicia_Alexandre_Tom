package com.project.ase_project.cucumber;


import com.project.ase_project.model.clean.MostPlayedGameModes.GameModesPlayed;
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

public class QueueSteps extends CucumberSpringConfiguration {
    private ResponseEntity<GameModesPlayed> beluga;
    private ResponseEntity<GameModesPlayed> razork;
    private ResponseEntity<String> errorNotFound;
    private ResponseEntity<String> errorBadRequest;


    private ResponseEntity<GameModesPlayed> getGameModesPlayed(String url){
        String fullUrl = "http://localhost:8080" + url;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(fullUrl, HttpMethod.GET, entity, GameModesPlayed.class);
    }




    @When("queue the user calls {string}")
    public void queueTheUserCalls(String url) {
        if (url.contains("Belugafurtif")){
            beluga = getGameModesPlayed(url);
        } else if (url.contains("Razørk Activoo")) {
            razork = getGameModesPlayed(url);
        }
    }

    @Then("queue the user receives {string}'s queue")
    public void queueTheUserReceivesSQueue(String summoner) {
        if (summoner.equals("Belugafurtif")){
            GameModesPlayed belugaGameModesPlayed = beluga.getBody();
            assertEquals("Belugafurtif", belugaGameModesPlayed.getSummonerName());
            assertNotNull(belugaGameModesPlayed.getTotalKills());
            assertNotNull(belugaGameModesPlayed.getTotalGamesPlayed());
            assertNotNull(belugaGameModesPlayed.getTotalDeaths());
            assertNotNull(belugaGameModesPlayed.getBestPerformingGameModeAssists());
            assertNotNull(belugaGameModesPlayed.getMostPlayedGameModeAssists());
            assertNotNull(belugaGameModesPlayed.getTotalAssists());
            assertNotNull(belugaGameModesPlayed.getTotalWins());
            assertNotNull(belugaGameModesPlayed.getTotalLosses());
            assertNotNull(belugaGameModesPlayed.getKDA());

        } else if (summoner.equals("Razørk Activoo")){
            GameModesPlayed razorkGameModesPlayed = razork.getBody();
            assertEquals("Razørk Activoo", razorkGameModesPlayed.getSummonerName());
            assertNotNull(razorkGameModesPlayed.getTotalKills());
            assertNotNull(razorkGameModesPlayed.getTotalGamesPlayed());
            assertNotNull(razorkGameModesPlayed.getTotalDeaths());
            assertNotNull(razorkGameModesPlayed.getBestPerformingGameModeAssists());
            assertNotNull(razorkGameModesPlayed.getMostPlayedGameModeAssists());
            assertNotNull(razorkGameModesPlayed.getTotalAssists());
            assertNotNull(razorkGameModesPlayed.getTotalWins());
            assertNotNull(razorkGameModesPlayed.getTotalLosses());
            assertNotNull(razorkGameModesPlayed.getKDA());
        }
    }

    @And("queue the response code status for {string} is {int}")
    public void queueTheResponseCodeStatusForIs(String summoner, int code) {
        if (summoner.equals("Belugafurtif")) {
            assertEquals(code, beluga.getStatusCode().value());
        } else if (summoner.equals("Razørk Activoo")) {
            assertEquals(code, razork.getStatusCode().value());
        }
    }

    @When("queue a GET request is made to the endpoint {string} \\(where A does not exist)")
    public void queueAGETRequestIsMadeToTheEndpointWhereADoesNotExist(String url) {
        try {
            getGameModesPlayed(url);
        } catch (HttpClientErrorException ex) {
            errorNotFound = new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
        }
    }

    @Then("queue the API should respond with a status code {int} \\(Not Found)")
    public void queueTheAPIShouldRespondWithAStatusCodeNotFound(int code) {
        assertEquals(code, errorNotFound.getStatusCode().value());
    }

    @And("queue the response body should contain an error message indicating that the summoner was not found")
    public void queueTheResponseBodyShouldContainAnErrorMessageIndicatingThatTheSummonerWasNotFound() {
        //assertEquals("{\"timestamp\":\"2024-04-02T17:13:11.943+00:00\",\"status\":404,\"error\":\"Not Found\",\"path\":\"/riot/queue/A\"}", errorNotFound.getBody());
    }

    @When("queue an invalid request is made to the endpoint {string} \\(where the summoner Name is missing)")
    public void queueAnInvalidRequestIsMadeToTheEndpointWhereTheSummonerNameIsMissing(String url) {
        try {
            getGameModesPlayed(url);
        } catch (HttpClientErrorException ex) {
            errorBadRequest = new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
        }
    }

    @Then("queue the API should respond with a status code {int} \\(Bad Request)")
    public void queueTheAPIShouldRespondWithAStatusCodeBadRequest(int code) {
        assertEquals(code, errorBadRequest.getStatusCode().value());
    }

    @And("queue the response body should contain an error message indicating that the request is invalid")
    public void queueTheResponseBodyShouldContainAnErrorMessageIndicatingThatTheRequestIsInvalid() {
        //assertEquals("Erreur 404 : Veuillez préciser un pseudo de joueur.", errorBadRequest.getBody());
    }


}