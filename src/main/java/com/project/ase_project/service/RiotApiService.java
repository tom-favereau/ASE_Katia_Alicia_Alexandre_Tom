package com.project.ase_project.service;

import java.lang.reflect.Array;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import com.project.ase_project.model.League;
import com.project.ase_project.model.Match;
import com.project.ase_project.model.Rank;
import com.project.ase_project.model.Summoner;
import com.project.ase_project.repository.MatchRepository;
import com.project.ase_project.repository.RankRepository;
import com.project.ase_project.repository.SummonerRepository;

@Service
public class RiotApiService {

    @Value("${riot.api.key}")
    private String apiKey;

    private final SummonerRepository summonerRepository;
    private final MatchRepository matchRepository;
    private final RankRepository rankRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public RiotApiService(RestTemplate restTemplate, SummonerRepository summonerRepository, MatchRepository matchRepository, RankRepository rankRepository) {
        this.restTemplate = restTemplate;
        this.summonerRepository = summonerRepository;
        this.matchRepository = matchRepository;
        this.rankRepository = rankRepository;
    }

    public Summoner getSummonerByName(String summonerName) {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key="+apiKey;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Summoner> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, Summoner.class);
        Summoner summoner = response.getBody();
        if (summoner != null) {
            summonerRepository.save(summoner);
        }
        return summoner;
    }

    public Match getMatchById(String matchId) {
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key="+apiKey;
        System.out.println(apiUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Match> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, Match.class);
        System.out.println("Voici le corps de la réponse : ");
        System.out.println(response.getBody().toString());
        System.out.println("Voici le code de statut de la réponse : ");
        System.out.println(response.getStatusCode());
        Match match = response.getBody();
        if (match != null) {
            matchRepository.save(match);
        }
        return match;
    }

    public List<League> getRankData(String encryptedSummonerId) {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + encryptedSummonerId; //+ "?api_key="+apiKey;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<League[]> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, League[].class);
        League[] rankArray = response.getBody();
        List<League> rankList = Arrays.asList(rankArray);
        /*if (rankList != null) {
            for (League rank : rankList) {
                rankRepository.save(rank);
            }
        }*/
        return rankList;
    }
}
