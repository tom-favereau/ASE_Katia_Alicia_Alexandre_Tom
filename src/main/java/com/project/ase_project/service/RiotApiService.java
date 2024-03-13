package com.project.ase_project.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.ase_project.model.champion.Champion;
import com.project.ase_project.repository.ChampionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.project.ase_project.model.Match;
import com.project.ase_project.model.Summoner;
import com.project.ase_project.repository.MatchRepository;
import com.project.ase_project.repository.RankRepository;
import com.project.ase_project.repository.SummonerRepository;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class RiotApiService {

    @Value("${riot.api.key}")
    private String apiKey;

    private final SummonerRepository summonerRepository;
    private final MatchRepository matchRepository;
    private final RankRepository rankRepository;
    private final RestTemplate restTemplate;
    private final ChampionRepository championRepository;

    @Autowired
    public RiotApiService(RestTemplate restTemplate, SummonerRepository summonerRepository, MatchRepository matchRepository, RankRepository rankRepository, ChampionRepository championRepository) {
        this.restTemplate = restTemplate;
        this.summonerRepository = summonerRepository;
        this.matchRepository = matchRepository;
        this.rankRepository = rankRepository;
        this.championRepository = championRepository;
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
        //ResponseEntity<String> response2 = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
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

    /*public List<Rank> getRankData(String encryptedSummonerId) {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + encryptedSummonerId + "?api_key="+apiKey;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Rank[]> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, Rank[].class);
        Rank[] rankList = response.getBody();
        if (rankList != null) {
            rankRepository.save(rankList);
        }
        return rankList;
    }*/

    @PostConstruct
    public void initializeChampions() throws IOException {
        //TODO enlever la variable en dur
        if (championRepository.count() != 167) {
            //Getting raw json
            JsonNode json = new ObjectMapper().readTree(new URL("https://ddragon.leagueoflegends.com/cdn/14.5.1/data/en_US/champion.json"));
            JsonNode championJson = json.get("data");
            // Iteration on json nodes
            HashMap<String, Champion> result = new HashMap<>();
            Iterator<String> championIterator = championJson.fieldNames();
            while (championIterator.hasNext()){
                String name = championIterator.next();
                JsonNode championNode = championJson.get(name);
                //Removing the blurb
                ((ObjectNode) championNode).remove("blurb");
                //Conversion to Champion type
                Champion champion = new ObjectMapper().treeToValue(championNode, Champion.class);
                result.put(name, champion);
            }
            //Saving all the champions in the repository
            for (String championName : result.keySet()) {
                championRepository.save(result.get(championName));
            }
            System.out.println("Champion table successfully initialized.");
        } else {
            System.out.println("Champion table already initialized.");
        }
    }
}
