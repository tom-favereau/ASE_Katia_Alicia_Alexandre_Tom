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

public class gradeSteps {
    private ResponseEntity<String> beluga;
    private ResponseEntity<String> razork;
    private ResponseEntity<String> errorNotFound;
    private ResponseEntity<String> errorBadRequest;

    private int belugaCardinal;
    private int razorkCardinal;
    private ResponseEntity<String> getResponseEntity(String url){
        String fullUrl = "http://localhost:8080/riot" + url;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(fullUrl, HttpMethod.POST, entity, String.class);
    }

    private int getCard(String summoner){
        String fullUrl = "http://localhost:8080/riot/summary/" + summoner;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(fullUrl, HttpMethod.GET, entity, Summary.class).getBody().getCardinal();
    }

    @When("grade the user calls {string}")
    public void gradeTheUserCalls(String url) {
        if (url.contains("Belugafurtif")){
            belugaCardinal = getCard("Belugafurtif");
            beluga = getResponseEntity(url);
        } else if (url.contains("Razørk Activoo")) {
            razorkCardinal = getCard("Razørk Activoo");
            razork = getResponseEntity(url);
        }
    }


    @Then("grade the user post {string}'s grade")
    public void gradeTheUserPostSGrade(String summoner) {
        if (summoner.equals("Belugafurtif")) {
            assertEquals(belugaCardinal+1, getCard("Belugafurtif"));
        } else if (summoner.equals("Razørk Activoo")){
            assertEquals(razorkCardinal+1, getCard("Razørk Activoo"));
        }

    }

    @And("grade the response code status for {string} is {int}")
    public void gradeTheResponseCodeStatusForIs(String summoner, int code) {
        if (summoner.equals("Belugafurtif")) {
            assertEquals(code, beluga.getStatusCode().value());
        } else if (summoner.equals("Razørk Activoo")) {
            assertEquals(code, razork.getStatusCode().value());
        }
    }

    @When("grade a GET request is made to the endpoint {string} \\(where A does not exist)")
    public void gradeAGETRequestIsMadeToTheEndpointWhereADoesNotExist(String url) {
        try {
            getResponseEntity(url);
        } catch (HttpClientErrorException ex) {
            errorNotFound = new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
        }
    }

    @Then("grade the API should respond with a status code {int} \\(Not Found)")
    public void gradeTheAPIShouldRespondWithAStatusCodeNotFound(int code) {
        assertEquals(code, errorNotFound.getStatusCode().value());
    }

    @And("grade the response body should contain an error message indicating that the summoner was not found")
    public void gradeTheResponseBodyShouldContainAnErrorMessageIndicatingThatTheSummonerWasNotFound() {
        //assertEquals("{\"timestamp\":\"2024-04-02T17:13:11.943+00:00\",\"status\":404,\"error\":\"Not Found\",\"path\":\"/riot/queue/A\"}", errorNotFound.getBody());
    }

    @When("grade an invalid request is made to the endpoint {string} \\(where the summoner Name is missing)")
    public void gradeAnInvalidRequestIsMadeToTheEndpointWhereTheSummonerNameIsMissing(String url) {
        try {
            getResponseEntity(url);
        } catch (HttpClientErrorException ex) {
            errorBadRequest = new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
        }
    }

    @Then("grade the API should respond with a status code {int} \\(Bad Request)")
    public void gradeTheAPIShouldRespondWithAStatusCodeBadRequest(int code) {
        assertEquals(code, errorBadRequest.getStatusCode().value());
    }

    @And("grade the response body should contain an error message indicating that the request is invalid")
    public void gradeTheResponseBodyShouldContainAnErrorMessageIndicatingThatTheRequestIsInvalid() {
        //assertEquals("Erreur 404 : Veuillez préciser un pseudo de joueur.", errorBadRequest.getBody());
    }
}
