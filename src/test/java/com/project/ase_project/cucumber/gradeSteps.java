package com.project.ase_project.cucumber;

import com.project.ase_project.model.clean.MostPlayedGameModes.GameModesPlayed;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class gradeSteps {
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
        return restTemplate.exchange(fullUrl, HttpMethod.POST, entity, GameModesPlayed.class);
    }


    @When("grade the user calls {string}")
    public void gradeTheUserCalls(String url) {
        if (url.contains("Belugafurtif")){
            beluga = getGameModesPlayed(url);
        } else if (url.contains("Razørk Activoo")) {
            razork = getGameModesPlayed(url);
        }
    }

    @Then("grade the user receives {string}'s summary")
    public void gradeTheUserReceivesSSummary(String arg0) {
        
    }

    @And("grade the response code status for {string} is {int}")
    public void gradeTheResponseCodeStatusForIs(String arg0, int arg1) {
        
    }

    @When("grade a GET request is made to the endpoint {string} \\(where A does not exist)")
    public void gradeAGETRequestIsMadeToTheEndpointWhereADoesNotExist(String arg0) {
        
    }

    @Then("grade the API should respond with a status code {int} \\(Not Found)")
    public void gradeTheAPIShouldRespondWithAStatusCodeNotFound(int arg0) {
        
    }

    @And("grade the response body should contain an error message indicating that the summoner was not found")
    public void gradeTheResponseBodyShouldContainAnErrorMessageIndicatingThatTheSummonerWasNotFound() {
        
    }

    @When("grade an invalid request is made to the endpoint {string} \\(where the summoner Name is missing)")
    public void gradeAnInvalidRequestIsMadeToTheEndpointWhereTheSummonerNameIsMissing(String arg0) {
        
    }

    @Then("grade the API should respond with a status code {int} \\(Bad Request)")
    public void gradeTheAPIShouldRespondWithAStatusCodeBadRequest(int arg0) {
        
    }

    @And("grade the response body should contain an error message indicating that the request is invalid")
    public void gradeTheResponseBodyShouldContainAnErrorMessageIndicatingThatTheRequestIsInvalid() {
    }
}
