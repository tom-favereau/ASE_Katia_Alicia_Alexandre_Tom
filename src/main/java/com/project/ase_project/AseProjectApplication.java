package com.project.ase_project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.ase_project.model.Match;
import com.project.ase_project.model.Rank;
import com.project.ase_project.model.RankList;
import com.project.ase_project.model.Summoner;
import com.project.ase_project.service.RiotApiService;

import java.io.IOException;

@RestController
@RequestMapping("/riot")
@SpringBootApplication
public class AseProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AseProjectApplication.class, args);
    }

    @Autowired
    private RiotApiService riotApiService;

    @GetMapping("/summoners/{summonerName}")
    public ResponseEntity<Summoner> getSummonerData(@PathVariable String summonerName) {
        Summoner summoner = riotApiService.getSummonerByName(summonerName);
        return new ResponseEntity<>(summoner, HttpStatus.OK);
    }

    /*@GetMapping("/rank/{encryptedSummonerId}")
    public ResponseEntity<RankList> getRankData(@PathVariable String encryptedSummonerId) {
        RankList rankList = riotApiService.getRankData(encryptedSummonerId);
        return new ResponseEntity<>(rankList, HttpStatus.OK);
    }*/

    @GetMapping("/matches/{matchId}")
    public ResponseEntity<Match> getMatchData(@PathVariable String matchId) {
        Match match = riotApiService.getMatchById(matchId);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }
}

