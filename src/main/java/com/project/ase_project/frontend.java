package com.project.ase_project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.ase_project.model.clean.league.League;
import com.project.ase_project.model.clean.match.Match;
import com.project.ase_project.model.clean.summary.Summary;
import com.project.ase_project.model.clean.summoner.Summoner;
import com.project.ase_project.service.RiotApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/riot")
@SpringBootApplication
public class frontend {

    public static void main(String[] args) {
        SpringApplication.run(frontend.class, args);
    }

    @Autowired
    private RiotApiService riotApiService;

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/summoner_page/{summonerName}")
    public String getSummonerData(@PathVariable String summonerName, Model model) {
        try {
            Summary summary = riotApiService.getSummary(summonerName);
            model.addAttribute("summoner", summary);
            return "summoner";
        }
        catch (Exception e) {
            model.addAttribute("summonerName", summonerName);
            return "not_found";
        }
    }

}