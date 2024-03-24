package com.project.ase_project.service;

import java.util.*;
import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

import lombok.Getter;

import com.project.ase_project.repository.*;

import com.project.ase_project.exception.*;

import com.project.ase_project.model.ddragon.champion.Champion;
import com.project.ase_project.model.ddragon.maps.LOLMap;
import com.project.ase_project.model.ddragon.queue.LOLQueue;

import com.project.ase_project.model.dto.summoner.SummonerDto;
import com.project.ase_project.model.dto.match.MatchDto;
import com.project.ase_project.model.dto.league.LeagueDto;

import com.project.ase_project.model.clean.grade.Grade;
import com.project.ase_project.model.clean.summary.Summary;
import com.project.ase_project.model.clean.league.League;
import com.project.ase_project.model.clean.match.Match;
import com.project.ase_project.model.clean.summoner.Summoner;

@Service
public class RiotApiService {

    @Value("${riot.api.key}")
    private String apiKey;

    private final MatchRepository matchRepository;
    private final GradeRepository gradeRepository;
    private final RestTemplate restTemplate;
    @Getter
    private final ChampionRepository championRepository;
    @Getter
    private final MapRepository mapRepository;
    @Getter
    private final QueueRepository queueRepository;

    @Autowired
    public RiotApiService(RestTemplate restTemplate, MatchRepository matchRepository, GradeRepository gradeRepository,
                          ChampionRepository championRepository, MapRepository mapRepository, QueueRepository queueRepository) {
        this.restTemplate = restTemplate;
        this.matchRepository = matchRepository;
        this.gradeRepository = gradeRepository;
        this.championRepository = championRepository;
        this.mapRepository = mapRepository;
        this.queueRepository = queueRepository;
    }

    public Summoner getSummonerByName(String summonerName) {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key="+apiKey;
        try {
            SummonerDto summonerDto = restTemplate.getForObject(apiUrl, SummonerDto.class);
            if (summonerDto != null) {
                Summoner summoner = summonerDto.toSummoner();
                Grade grade = gradeRepository.findById(summoner.getId()).orElse(null);
                if (grade == null) {
                    summoner.setAverage(0);
                    summoner.setCardinal(0);
                } else {
                    summoner.setAverage(grade.getAverage());
                    summoner.setCardinal(grade.getCardinal());
                }
                return summoner;
            }
            else {
                //System.out.println("Summoner not found");
                throw new SummonerNotFoundException("Erreur 404 : Le joueur " + summonerName + " n'existe pas.");
            }
        } catch (HttpClientErrorException.BadRequest e) {
            throw new BadRequestException("Erreur 400 : Bad request");
        }
        catch (HttpClientErrorException.Unauthorized e) {
            throw new BadRequestException("Erreur 401 : Unauthorized");
        }
        catch (HttpClientErrorException.Forbidden e) {
            throw new BadRequestException("Erreur 403 : Forbidden");
        }
        catch (HttpClientErrorException.NotFound e) {
            throw new SummonerNotFoundException("Erreur 404 : Le joueur " + summonerName + " n'existe pas.");
        }
        catch (HttpClientErrorException.MethodNotAllowed e) {
            throw new MethodNotAllowed("Erreur 405 : Method not allowed");
        }
        catch (HttpClientErrorException.UnsupportedMediaType e) {
            throw new UnsupportedMediaType("Erreur 415 : Unsupported media type");
        }
        catch (HttpClientErrorException.TooManyRequests e) {
            throw new RateLimitExceededException("Erreur 429 : Too many requests");
        }
        catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerError("Erreur 500 : Internal server error");
        }
        catch (HttpServerErrorException.BadGateway e) {
            throw new BadGateway("Erreur 502 : Bad gateway");
        }
        catch (HttpServerErrorException.ServiceUnavailable e) {
            throw new ServiceUnavailable("Erreur 503 : Service unavailable");
        }
        catch (HttpServerErrorException.GatewayTimeout e) {
            throw new GatewayTimeout("Erreur 504 : Gateway timeout");
        }
    }

    public Match getMatchById(String matchId) {
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key="+apiKey;
        try {
            MatchDto matchDto = restTemplate.getForObject(apiUrl, MatchDto.class);
            if (matchDto != null) {
                Match match = matchDto.toMatch();
                matchRepository.save(match);
                return match;
            }
            else {
                throw new MatchNotFoundException("Erreur 404 : Le match " + matchId + " n'existe pas.");
            }
        } catch (HttpClientErrorException.BadRequest e) {
            throw new BadRequestException("Erreur 400 : Bad request");
        }
        catch (HttpClientErrorException.Unauthorized e) {
            throw new BadRequestException("Erreur 401 : Unauthorized");
        }
        catch (HttpClientErrorException.Forbidden e) {
            throw new BadRequestException("Erreur 403 : Forbidden");
        }
        catch (HttpClientErrorException.NotFound e) {
            throw new MatchNotFoundException("Erreur 404 : Le match " + matchId + " n'existe pas.");
        }
        catch (HttpClientErrorException.MethodNotAllowed e) {
            throw new MethodNotAllowed("Erreur 405 : Method not allowed");
        }
        catch (HttpClientErrorException.UnsupportedMediaType e) {
            throw new UnsupportedMediaType("Erreur 415 : Unsupported media type");
        }
        catch (HttpClientErrorException.TooManyRequests e) {
            throw new RateLimitExceededException("Erreur 429 : Too many requests");
        }
        catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerError("Erreur 500 : Internal server error");
        }
        catch (HttpServerErrorException.BadGateway e) {
            throw new BadGateway("Erreur 502 : Bad gateway");
        }
        catch (HttpServerErrorException.ServiceUnavailable e) {
            throw new ServiceUnavailable("Erreur 503 : Service unavailable");
        }
        catch (HttpServerErrorException.GatewayTimeout e) {
            throw new GatewayTimeout("Erreur 504 : Gateway timeout");
        }
    }

    public ArrayList<League> getRankData(String encryptedSummonerId) {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + encryptedSummonerId + "?api_key="+apiKey;
        try {
            LeagueDto[] leaguesDto = restTemplate.getForObject(apiUrl, LeagueDto[].class);
            assert leaguesDto != null;
            if (leaguesDto.length > 0) {
                ArrayList<League> leagues = new ArrayList<>();
                for (LeagueDto leagueDto : leaguesDto) {
                    League league = leagueDto.toLeague();
                    leagues.add(league);
                }
                if (leagues.size() == 1 && leagues.get(0).getQueueType().equals("RANKED_FLEX_SR")) {
                    leagues.add(new League("", encryptedSummonerId, "", "RANKED_SOLO_5x5", "UNRANKED", "", 0, 0, 0));
                }
                return leagues;
            }
            else {
                return new ArrayList<>();
            }
        } catch (HttpClientErrorException.BadRequest e) {
            throw new BadRequestException("Erreur 400 : Bad request");
        }
        catch (HttpClientErrorException.Unauthorized e) {
            throw new BadRequestException("Erreur 401 : Unauthorized");
        }
        catch (HttpClientErrorException.Forbidden e) {
            throw new BadRequestException("Erreur 403 : Forbidden");
        }
        catch (HttpClientErrorException.NotFound e) {
            throw new LeaguesNotFoundException("Erreur 404 : Le joueur avec l'identifiant " + encryptedSummonerId + " n'a pas de classement.");
        }
        catch (HttpClientErrorException.MethodNotAllowed e) {
            throw new MethodNotAllowed("Erreur 405 : Method not allowed");
        }
        catch (HttpClientErrorException.UnsupportedMediaType e) {
            throw new UnsupportedMediaType("Erreur 415 : Unsupported media type");
        }
        catch (HttpClientErrorException.TooManyRequests e) {
            throw new RateLimitExceededException("Erreur 429 : Too many requests");
        }
        catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerError("Erreur 500 : Internal server error");
        }
        catch (HttpServerErrorException.BadGateway e) {
            throw new BadGateway("Erreur 502 : Bad gateway");
        }
        catch (HttpServerErrorException.ServiceUnavailable e) {
            throw new ServiceUnavailable("Erreur 503 : Service unavailable");
        }
        catch (HttpServerErrorException.GatewayTimeout e) {
            throw new GatewayTimeout("Erreur 504 : Gateway timeout");
        }
    }

    public Summary getSummary(String summonerName) {
        try {
            Summoner summoner = getSummonerByName(summonerName);
            ArrayList<League> leagues = getRankData(summoner.getId());
            return new Summary(summoner, leagues);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }
        catch (SummonerNotFoundException e) {
            throw new SummonerNotFoundException(e.getMessage());
        }
        catch (LeaguesNotFoundException e) {
            throw new LeaguesNotFoundException(e.getMessage());
        }
        catch (MethodNotAllowed e) {
            throw new MethodNotAllowed(e.getMessage());
        }
        catch (UnsupportedMediaType e) {
            throw new UnsupportedMediaType(e.getMessage());
        }
        catch (RateLimitExceededException e) {
            throw new RateLimitExceededException(e.getMessage());
        }
        catch (InternalServerError e) {
            throw new InternalServerError(e.getMessage());
        }
        catch (BadGateway e) {
            throw new BadGateway(e.getMessage());
        }
        catch (ServiceUnavailable e) {
            throw new ServiceUnavailable(e.getMessage());
        }
        catch (GatewayTimeout e) {
            throw new GatewayTimeout(e.getMessage());
        }
    }

    public void postGrade(String summonerName, int note) {
        Summoner summoner = getSummonerByName(summonerName);
        Grade grade = gradeRepository.findById(summoner.getId()).orElse(null);
        if (grade == null) {
            grade = new Grade(summoner.getId(), summoner.getName(), note, 1);
            gradeRepository.save(grade);
        } else {
            float average = grade.getAverage();
            int cardinal = grade.getCardinal();
            grade.setAverage((average * cardinal + note) / (cardinal + 1));
            grade.setCardinal(cardinal + 1);
            gradeRepository.save(grade);
        }
    }

    @PostConstruct
    public boolean initializeChampions() throws IOException {
        String[] remove = new String[]{"blurb", "version", "title", "info", "tags", "partype", "stats"};
        //Getting raw json
        JsonNode json = new ObjectMapper().readTree(new URL("https://ddragon.leagueoflegends.com/cdn/14.5.1/data/en_US/champion.json"));
        JsonNode championJson = json.get("data");
        //Resetting the table if new maps have been added or if table is not initialized.
        boolean originalStateIsNotValid = championRepository.count() != championJson.size();
        if (originalStateIsNotValid) {
            // Iteration over json nodes
            HashMap<String, Champion> result = new HashMap<>();
            Iterator<String> championIterator = championJson.fieldNames();
            while (championIterator.hasNext()){
                String name = championIterator.next();
                ObjectNode championNode = (ObjectNode)championJson.get(name);
                //Removing useless fields
                for (String field : remove){
                    championNode.remove(field);
                }
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
        return originalStateIsNotValid;
    }

    @PostConstruct
    public boolean initializeMaps() throws IOException {
        //Getting raw json
        JsonNode mapArrayJson = new ObjectMapper().readTree(new URL("https://static.developer.riotgames.com/docs/lol/maps.json"));
        //Resetting the table if new maps have been added or if table is not initialized.
        boolean originalStateIsNotValid = mapRepository.count() != mapArrayJson.size();
        if (originalStateIsNotValid) {
            //Iterate over array.
            for (JsonNode mapJson : mapArrayJson) {
                //Mapping to LOLMap
                LOLMap map = new ObjectMapper().treeToValue(mapJson, LOLMap.class);
                //Saving to repository
                mapRepository.save(map);
            }
            System.out.println("Map table successfully initialized.");
        } else {
            System.out.println("Map table already initialized.");
        }
        return originalStateIsNotValid;
    }

    @PostConstruct
    public boolean initializeQueues() throws IOException {
        //Getting raw json
        JsonNode queueArrayJson = new ObjectMapper().readTree(new URL("https://static.developer.riotgames.com/docs/lol/queues.json"));
        //Resetting the table if new queues have been added or if table is not initialized.
        boolean originalStateIsNotValid = queueRepository.count() != queueArrayJson.size();
        if (originalStateIsNotValid) {
            //Iterate over array.
            for (JsonNode queueJson : queueArrayJson) {
                //Removing unnecessary field.
                ObjectNode queueObjectNode = (ObjectNode) queueJson;
                queueObjectNode.remove("notes");
                //Mapping to LOLQueue.
                LOLQueue queue = new ObjectMapper().treeToValue(queueObjectNode, LOLQueue.class);
                //Saving to repository
                queueRepository.save(queue);
            }
            System.out.println("Queue table successfully initialized.");
        } else {
            System.out.println("Queue table already initialized.");
        }
        return originalStateIsNotValid;
    }
}
