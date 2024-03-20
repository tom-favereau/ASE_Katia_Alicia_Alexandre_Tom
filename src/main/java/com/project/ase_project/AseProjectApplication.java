package com.project.ase_project;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import com.project.ase_project.model.clean.match.Match;
import com.project.ase_project.model.clean.league.League;
import com.project.ase_project.model.clean.summoner.Summoner;
import com.project.ase_project.service.RiotApiService;

@Controller
@RequestMapping("/riot")
@SpringBootApplication
public class AseProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AseProjectApplication.class, args);
    }

    @Autowired
    private RiotApiService riotApiService;

    // Exemple : http://localhost:8080/riot/summoners/Belugafurtif
    @GetMapping("/summoners/{summonerName}")
    public ResponseEntity<Summoner> getSummonerData(@PathVariable String summonerName) {
        Summoner summoner = riotApiService.getSummonerByName(summonerName);
        return new ResponseEntity<>(summoner, HttpStatus.OK);
    }

    @GetMapping("/summoner_page/{summonerName}")
    public String getSummonerData(@PathVariable String summonerName, Model model) {
        try {
            Summoner summoner = riotApiService.getSummonerByName(summonerName);
            model.addAttribute("summoner", summoner);
            return "summoner";
        }
        catch (Exception e) {
            return "not_found";
        }
    }

    // Exemple : http://localhost:8080/riot/rank/F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ
    @GetMapping("/rank/{encryptedSummonerId}")
    public ResponseEntity<ArrayList<League>> getRankData(@PathVariable String encryptedSummonerId) {
        ArrayList<League> rankList = riotApiService.getRankData(encryptedSummonerId);
        return new ResponseEntity<>(rankList, HttpStatus.OK);
    }

    // Exemple : http://localhost:8080/riot/matches/EUW1_6760205418
    @GetMapping("/matches/{matchId}")
    public ResponseEntity<Match> getMatchData(@PathVariable String matchId) throws JsonProcessingException {
        Match match = riotApiService.getMatchById(matchId);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }


}