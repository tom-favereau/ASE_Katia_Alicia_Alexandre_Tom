package com.project.ase_project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;


import com.project.ase_project.model.dto.league.LeagueDto;
import com.project.ase_project.model.dto.match.MatchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.ase_project.model.dto.summoner.SummonerDto;
import com.project.ase_project.model.dto.match.MatchDto;
import com.project.ase_project.model.dto.league.LeagueDto;

import com.project.ase_project.model.clean.league.League;
import com.project.ase_project.model.clean.match.Match;
import com.project.ase_project.model.clean.summoner.Summoner;

import com.project.ase_project.repository.MatchRepository;
import com.project.ase_project.repository.LeagueRepository;
import com.project.ase_project.repository.SummonerRepository;

@Service
public class RiotApiService {

    @Value("${riot.api.key}")
    private String apiKey;

    private final SummonerRepository summonerRepository;
    private final MatchRepository matchRepository;
    private final LeagueRepository leagueRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public RiotApiService(RestTemplate restTemplate, SummonerRepository summonerRepository, MatchRepository matchRepository, LeagueRepository leagueRepository) {
        this.restTemplate = restTemplate;
        this.summonerRepository = summonerRepository;
        this.matchRepository = matchRepository;
        this.leagueRepository = leagueRepository;
    }

    public Summoner getSummonerByName(String summonerName) {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key="+apiKey;
        SummonerDto summonerDto = restTemplate.getForObject(apiUrl, SummonerDto.class);
        if (summonerDto != null) {
            Summoner summoner = SummonerDto.toSummoner(summonerDto);
            summonerRepository.save(summoner);
            return summoner;
        }
        else {
            throw new RuntimeException("Summoner not found");
        }
    }

    public Match getMatchById(String matchId) throws JsonProcessingException {
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key="+apiKey;
        MatchDto matchDto = restTemplate.getForObject(apiUrl, MatchDto.class);
        if (matchDto != null) {
            Match match = new Match();
            match.setMatchId(matchDto.getMetadata().getMatchId());
            matchRepository.save(match);
            return match;
        }
        return null;
    }

    public ArrayList<League> getRankData(String encryptedSummonerId) {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + encryptedSummonerId + "?api_key="+apiKey;
        LeagueDto[] leaguesDto = restTemplate.getForObject(apiUrl, LeagueDto[].class);
        if (leaguesDto != null) {
            ArrayList<League> leagues = new ArrayList<>();
            for (LeagueDto leagueDto : leaguesDto) {
                League league = new League();
                league.setLeagueId(leagueDto.getLeagueId());
                leagueRepository.save(league);
                leagues.add(league);
            }
            return leagues;
        }
        return null;
    }
}
