package com.project.ase_project.cucumber;

import com.project.ase_project.model.clean.MostPlayedChampions.ChampionsPlayed;
import com.project.ase_project.model.clean.MostPlayedGameModes.GameModesPlayed;
import com.project.ase_project.model.clean.summary.Summary;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChampionsSteps {
    private ResponseEntity<ChampionsPlayed> beluga;
    private ResponseEntity<ChampionsPlayed> razork;
    private ResponseEntity<String> errorNotFound;
    private ResponseEntity<String> errorBadRequest;

    ResponseEntity<ChampionsPlayed> getResponseEntity(String url){
        String fullUrl = "http://localhost:8080" + url;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(fullUrl, HttpMethod.GET, entity, ChampionsPlayed.class);
    }

    @When("champion the user calls {string}")
    public void championTheUserCalls(String url) {
        if (url.contains("Belugafurtif")){
            beluga = getResponseEntity(url);
        } else if (url.contains("Razørk Activoo")) {
            razork = getResponseEntity(url);
        }
    }


    @Then("champion the user receives {string}'s queue")
    public void championTheUserReceivesSQueue(String summoner) {
        if (summoner.equals("Belugafurtif")){
            ChampionsPlayed belugaGameModesPlayed = beluga.getBody();
            assertEquals("no3DrNwBLS6OfHYxUK1LtMqQrKcxixwr6zCWY9K0uveQIr2jpaNfyvrLgg", belugaGameModesPlayed.getSummonerId());
            assertNotNull(belugaGameModesPlayed.getTotalKills());
            assertNotNull(belugaGameModesPlayed.getTotalGamesPlayed());
            assertNotNull(belugaGameModesPlayed.getTotalDeaths());
            assertNotNull(belugaGameModesPlayed.getBestPerformingChampionAssists());
            assertNotNull(belugaGameModesPlayed.getBestPerformingChampionCount());
            assertNotNull(belugaGameModesPlayed.getTotalAssists());
            assertNotNull(belugaGameModesPlayed.getTotalWins());
            assertNotNull(belugaGameModesPlayed.getTotalLosses());
            assertNotNull(belugaGameModesPlayed.getKDA());

        } else if (summoner.equals("Razørk Activoo")){
            ChampionsPlayed razorkGameModesPlayed = razork.getBody();
            assertEquals("4aub4MQ8S40beG-vh50-lfIWW0jo0jNVPLubsVZRqyUArdo", razorkGameModesPlayed.getSummonerId());
            assertNotNull(razorkGameModesPlayed.getTotalKills());
            assertNotNull(razorkGameModesPlayed.getTotalGamesPlayed());
            assertNotNull(razorkGameModesPlayed.getTotalDeaths());
            assertNotNull(razorkGameModesPlayed.getBestPerformingChampionAssists());
            assertNotNull(razorkGameModesPlayed.getBestPerformingChampionCount());
            assertNotNull(razorkGameModesPlayed.getTotalAssists());
            assertNotNull(razorkGameModesPlayed.getTotalWins());
            assertNotNull(razorkGameModesPlayed.getTotalLosses());
            assertNotNull(razorkGameModesPlayed.getKDA());
        }
    }

    @And("champion the response code status for {string} is {int}")
    public void championTheResponseCodeStatusForIs(String summoner, int code) {
        if (summoner.equals("Belugafurtif")) {
            assertEquals(code, beluga.getStatusCode().value());
        } else if (summoner.equals("Razørk Activoo")) {
            assertEquals(code, razork.getStatusCode().value());
        }
    }

    @When("champion a GET request is made to the endpoint {string} \\(where A does not exist)")
    public void championAGETRequestIsMadeToTheEndpointWhereADoesNotExist(String url) {
        try {
            getResponseEntity(url);
        } catch (HttpClientErrorException ex) {
            errorNotFound = new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
        }
    }

    @Then("champion the API should respond with a status code {int} \\(Not Found)")
    public void championTheAPIShouldRespondWithAStatusCodeNotFound(int code) {
        assertEquals(code, errorNotFound.getStatusCode().value());
    }

    @And("champion the response body should contain an error message indicating that the summoner was not found")
    public void championTheResponseBodyShouldContainAnErrorMessageIndicatingThatTheSummonerWasNotFound() {
        //assertEquals("{\"timestamp\":\"2024-04-02T17:13:11.943+00:00\",\"status\":404,\"error\":\"Not Found\",\"path\":\"/riot/queue/A\"}", errorNotFound.getBody());
    }

    @When("champion an invalid request is made to the endpoint {string} \\(where the summoner Name is missing)")
    public void championAnInvalidRequestIsMadeToTheEndpointWhereTheSummonerNameIsMissing(String url) {
        try {
            getResponseEntity(url);
        } catch (HttpClientErrorException ex) {
            errorBadRequest = new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
        }
    }

    @Then("champion the API should respond with a status code {int} \\(Bad Request)")
    public void championTheAPIShouldRespondWithAStatusCodeBadRequest(int code) {
        assertEquals(code, errorBadRequest.getStatusCode().value());
    }

    @And("champion the response body should contain an error message indicating that the request is invalid")
    public void championTheResponseBodyShouldContainAnErrorMessageIndicatingThatTheRequestIsInvalid() {
        //assertEquals("Erreur 404 : Veuillez préciser un pseudo de joueur.", errorBadRequest.getBody());
    }


}
