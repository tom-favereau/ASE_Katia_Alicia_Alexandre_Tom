package com.project.ase_project.service;

import java.util.*;
import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.project.ase_project.model.clean.MostPlayedChampions.ChampionsPlayed;
import com.project.ase_project.model.clean.MostPlayedGameModes.GameModesPlayed;
import com.project.ase_project.model.clean.match.Metadata;
import com.project.ase_project.model.clean.match.Participant;
import lombok.Setter;
import lombok.ToString;
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

    @Getter
    @Setter
    @ToString
    private static class ChampionData {
        private int id;
        private String name;

        private int count;
        private int wins;
        private int losses;
        private float winRate;

        private int kills;
        private int deaths;
        private int assists;
        private float kda;
    }

    @Getter
    @Setter
    @ToString
    private static class GameModeData {
        private int id;
        private String name;

        private int count;
        private int wins;
        private int losses;
        private float winRate;

        private int kills;
        private int deaths;
        private int assists;
        private float kda;
    }

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

    /*
     *******************************************************************************************************************
     ************************************************* PARTIE API RIOT *************************************************
     *******************************************************************************************************************
     */

    public Summoner getSummonerByName(String summonerName) {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key="+apiKey;
        try {
            SummonerDto summonerDto = restTemplate.getForObject(apiUrl, SummonerDto.class);
            if (summonerDto != null) {
                Summoner summoner = summonerDto.toSummoner();
                Optional<Grade> grade = gradeRepository.findById(summoner.getId());
                if (grade.isEmpty()) {
                    summoner.setAverage(0);
                    summoner.setCardinal(0);
                } else {
                    summoner.setAverage(grade.get().getAverage());
                    summoner.setCardinal(grade.get().getCardinal());
                }
                return summoner;
            }
            else {
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

    public ArrayList<League> getRankById(String encryptedSummonerId) {
        String apiUrl = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + encryptedSummonerId + "?api_key="+apiKey;
        try {
            LeagueDto[] leaguesDto = restTemplate.getForObject(apiUrl, LeagueDto[].class);
            if (leaguesDto == null) {
                throw new LeaguesNotFoundException("Erreur 404 : Le joueur avec l'identifiant " + encryptedSummonerId + " n'a pas de classement.");
            } else if (leaguesDto.length > 0) {
                ArrayList<League> leagues = new ArrayList<>();
                for (LeagueDto leagueDto : leaguesDto) {
                    League league = leagueDto.toLeague();
                    leagues.add(league);
                }
                if (leagues.size() == 1 && leagues.get(0).getQueueType().equals("RANKED_FLEX_SR")) {
                    leagues.add(new League("", encryptedSummonerId, "", "RANKED_SOLO_5x5", "UNRANKED", "", 0, 0, 0));
                } else if (leagues.size() == 1 && leagues.get(0).getQueueType().equals("RANKED_SOLO_5x5")) {
                    leagues.add(new League("", encryptedSummonerId, "", "RANKED_FLEX_SR", "UNRANKED", "", 0, 0, 0));
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
    // TODO : getMatchById en parallèle
    public ArrayList<Match> getMatches(String summonerName, long startTime, long endTime, int queue, String type, int start, int count) {
        Summoner summoner = getSummonerByName(summonerName);
        String apiUrl = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summoner.getPuuid() + "/ids?start=" + start + "&count=" + count + "&api_key=" + apiKey;
        try {
            String[] matches = restTemplate.getForObject(apiUrl, String[].class);
            ArrayList<Match> matchList = new ArrayList<>();
            int counter = 0;
            if (matches != null) {
                for (String match : matches){
                    Optional<Match> matchEntity = matchRepository.findById(match);
                    if (matchEntity.isPresent()) {
                        matchList.add(matchEntity.get());
                    } else {
                        matchList.add(getMatchById(match));
                    }
                    if (counter++ > count){
                        break;
                    }
                }
            }
            return matchList;
        }
        catch (HttpClientErrorException.BadRequest e) {
            throw new BadRequestException("Erreur 400 : Bad request");
        }
        catch (HttpClientErrorException.Unauthorized e) {
            throw new BadRequestException("Erreur 401 : Unauthorized");
        }
        catch (HttpClientErrorException.Forbidden e) {
            throw new BadRequestException("Erreur 403 : Forbidden");
        }
        catch (HttpClientErrorException.NotFound e) {
            throw new SummonerNotFoundException("Erreur 404 : Le puuid " + summoner.getPuuid() + " n'existe pas.");
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
        } catch (SummonerNotFoundException e) {
            throw new SummonerNotFoundException(e.getMessage());
        } catch (MatchNotFoundException e) {
            throw new MatchNotFoundException(e.getMessage());
        }
    }

    /*
     *******************************************************************************************************************
     ************************************************ PARTIE TRAITEMENT ************************************************
     *******************************************************************************************************************
     */

    public Summary getSummaryByName(String summonerName) {
        try {
            Summoner summoner = getSummonerByName(summonerName);
            ArrayList<League> leagues = getRankById(summoner.getId());
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
        try {
            Summoner summoner = getSummonerByName(summonerName);
            Optional<Grade> grade = gradeRepository.findById(summoner.getId());
            if (grade.isEmpty()) {
                gradeRepository.save(new Grade(summoner.getId(), summoner.getName(), note, 1));
            } else {
                float average = grade.get().getAverage();
                int cardinal = grade.get().getCardinal();
                grade.get().setAverage((average * cardinal + note) / (cardinal + 1));
                grade.get().setCardinal(cardinal + 1);
                gradeRepository.save(grade.get());
            }
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }
        catch (SummonerNotFoundException e) {
            throw new SummonerNotFoundException(e.getMessage());
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

    public ChampionsPlayed getChampionsPlayedByName(String summonerName) {
        ChampionsPlayed championsPlayed = new ChampionsPlayed();
        ArrayList<Match> matches = getMatches(summonerName, 0, 0, 0, "", 0, 20);
        HashMap<String, ChampionData> champions = new HashMap<>();
        int participantId = 0;

        for (Match match : matches) {
            // Identification de la position du joueur dans la partie
            for (Participant participant : match.getParticipants()) {
                if (participant.getSummonerName().equals(summonerName)) {
                    participantId = participant.getParticipantId()-1;
                    break;
                }

            }
            // Récupération des données du participant
            Participant participant = match.getParticipants().get(participantId);
            ChampionData championData;
            if (!champions.containsKey(participant.getChampionName())) {
                championData = new ChampionData();
                championData.id = participant.getChampionId();
                championData.name = participant.getChampionName();
                championData.count = 1;
                championData.wins = participant.isWin() ? 1 : 0;
                championData.losses = participant.isWin() ? 0 : 1;
                championData.kills = participant.getKills();
                championData.deaths = participant.getDeaths();
                championData.assists = participant.getAssists();
            } else {
                championData = champions.get(participant.getChampionName());
                championData.count++;
                championData.wins += participant.isWin() ? 1 : 0;
                championData.losses += participant.isWin() ? 0 : 1;
                championData.kills += participant.getKills();
                championData.deaths += participant.getDeaths();
                championData.assists += participant.getAssists();
            }
            champions.put(participant.getChampionName(), championData);
        }
        // Calcul des statistiques
        for (ChampionData championData : champions.values()) {
            championData.winRate = ((float) championData.wins / championData.count);
            if (championData.deaths == 0) {
                championData.kda = Integer.MAX_VALUE;
            } else {
                championData.kda = ((float) (championData.kills + championData.assists) / championData.deaths);
            }
        }
        // Création de l'objet de retour
        // Summoner fields
        championsPlayed.setSummonerId(matches.get(0).getParticipants().get(participantId).getSummonerId());
        championsPlayed.setSummonerName(summonerName);

        championsPlayed.setTotalGamesPlayed(matches.size());
        championsPlayed.setTotalWins(champions.values().stream().mapToInt(championData -> championData.wins).sum());
        championsPlayed.setTotalLosses(champions.values().stream().mapToInt(championData -> championData.losses).sum());
        championsPlayed.setWinRate((float) championsPlayed.getTotalWins() / championsPlayed.getTotalGamesPlayed());

        championsPlayed.setTotalKills(champions.values().stream().mapToInt(championData -> championData.kills).sum());
        championsPlayed.setTotalDeaths(champions.values().stream().mapToInt(championData -> championData.deaths).sum());
        championsPlayed.setTotalAssists(champions.values().stream().mapToInt(championData -> championData.assists).sum());
        if (championsPlayed.getTotalDeaths() == 0) {
            championsPlayed.setKDA(Integer.MAX_VALUE);
        } else {
            championsPlayed.setKDA((float) (championsPlayed.getTotalKills() + championsPlayed.getTotalAssists()) / championsPlayed.getTotalDeaths());
        }

        // Most Played Champion fields
        ChampionData mostPlayedChampion = Collections.max(champions.values(), Comparator.comparing(ChampionData::getCount));
        championsPlayed.setMostPlayedChampionId(mostPlayedChampion.getId());
        championsPlayed.setMostPlayedChampionName(mostPlayedChampion.getName());

        championsPlayed.setMostPlayedChampionCount(mostPlayedChampion.getCount());
        championsPlayed.setMostPlayedChampionWins(mostPlayedChampion.getWins());
        championsPlayed.setMostPlayedChampionLosses(mostPlayedChampion.getLosses());
        championsPlayed.setMostPlayedChampionWinRate(mostPlayedChampion.getWinRate());

        championsPlayed.setMostPlayedChampionKills(mostPlayedChampion.getKills());
        championsPlayed.setMostPlayedChampionDeaths(mostPlayedChampion.getDeaths());
        championsPlayed.setMostPlayedChampionAssists(mostPlayedChampion.getAssists());
        championsPlayed.setMostPlayedChampionKDA(mostPlayedChampion.getKda());

        // Best Performing Champion fields
        ChampionData bestPerformingChampion = Collections.max(champions.values(), Comparator.comparing(ChampionData::getWinRate));
        championsPlayed.setBestPerformingChampionId(bestPerformingChampion.getId());
        championsPlayed.setBestPerformingChampionName(bestPerformingChampion.getName());

        championsPlayed.setBestPerformingChampionCount(bestPerformingChampion.getCount());
        championsPlayed.setBestPerformingChampionWins(bestPerformingChampion.getWins());
        championsPlayed.setBestPerformingChampionLosses(bestPerformingChampion.getLosses());
        championsPlayed.setBestPerformingChampionWinRate(bestPerformingChampion.getWinRate());

        championsPlayed.setBestPerformingChampionKills(bestPerformingChampion.getKills());
        championsPlayed.setBestPerformingChampionDeaths(bestPerformingChampion.getDeaths());
        championsPlayed.setBestPerformingChampionAssists(bestPerformingChampion.getAssists());
        championsPlayed.setBestPerformingChampionKDA(bestPerformingChampion.getKda());

        // Worst Performing Champion fields
        ChampionData worstPerformingChampion = Collections.min(champions.values(), Comparator.comparing(ChampionData::getKda));
        championsPlayed.setWorstPerformingChampionId(worstPerformingChampion.getId());
        championsPlayed.setWorstPerformingChampionName(worstPerformingChampion.getName());

        championsPlayed.setWorstPerformingChampionCount(worstPerformingChampion.getCount());
        championsPlayed.setWorstPerformingChampionWins(worstPerformingChampion.getWins());
        championsPlayed.setWorstPerformingChampionLosses(worstPerformingChampion.getLosses());
        championsPlayed.setWorstPerformingChampionWinRate(worstPerformingChampion.getWinRate());

        championsPlayed.setWorstPerformingChampionKills(worstPerformingChampion.getKills());
        championsPlayed.setWorstPerformingChampionDeaths(worstPerformingChampion.getDeaths());
        championsPlayed.setWorstPerformingChampionAssists(worstPerformingChampion.getAssists());
        championsPlayed.setWorstPerformingChampionKDA(worstPerformingChampion.getKda());

        // Second Most Played Champion fields
        champions.remove(Collections.max(champions.values(), Comparator.comparing(ChampionData::getCount)).getName());
        ChampionData secondMostPlayedChampion = Collections.max(champions.values(), Comparator.comparing(ChampionData::getCount));
        championsPlayed.setSecondMostPlayedChampionId(secondMostPlayedChampion.getId());
        championsPlayed.setSecondMostPlayedChampionName(secondMostPlayedChampion.getName());

        championsPlayed.setSecondMostPlayedChampionCount(secondMostPlayedChampion.getCount());
        championsPlayed.setSecondMostPlayedChampionWins(secondMostPlayedChampion.getWins());
        championsPlayed.setSecondMostPlayedChampionLosses(secondMostPlayedChampion.getLosses());
        championsPlayed.setSecondMostPlayedChampionWinRate(secondMostPlayedChampion.getWinRate());

        championsPlayed.setSecondMostPlayedChampionKills(secondMostPlayedChampion.getKills());
        championsPlayed.setSecondMostPlayedChampionDeaths(secondMostPlayedChampion.getDeaths());
        championsPlayed.setSecondMostPlayedChampionAssists(secondMostPlayedChampion.getAssists());
        championsPlayed.setSecondMostPlayedChampionKDA(secondMostPlayedChampion.getKda());

        return championsPlayed;
    }

    public GameModesPlayed getGameModesPlayedByName(String summonerName) {
        GameModesPlayed gameModesPlayed = new GameModesPlayed();
        ArrayList<Match> matches = getMatches(summonerName, 0, 0, 0, "", 0, 20);
        HashMap<String, GameModeData> gameModes = new HashMap<>();
        int participantId = 0;

        for (Match match : matches) {
            // Identification de la position du joueur dans la partie
            for (Participant participant : match.getParticipants()) {
                if (participant.getSummonerName().equals(summonerName)) {
                    participantId = participant.getParticipantId()-1;
                    break;
                }

            }
            // Récupération des données du participant
            Metadata metadata = match.getMetadata();
            Participant participant = match.getParticipants().get(participantId);
            GameModeData gameModeData;
            Optional<LOLQueue> queue = queueRepository.findById(metadata.getQueueId());
            if (queue.isPresent()) {
                if (!gameModes.containsKey(queue.get().getDescription())) {
                    gameModeData = new GameModeData();
                    gameModeData.id = queue.get().getQueueId();
                    gameModeData.name = queue.get().getDescription();
                    gameModeData.count = 1;
                    gameModeData.wins = participant.isWin() ? 1 : 0;
                    gameModeData.losses = participant.isWin() ? 0 : 1;
                    gameModeData.kills = participant.getKills();
                    gameModeData.deaths = participant.getDeaths();
                    gameModeData.assists = participant.getAssists();
                } else {
                    gameModeData = gameModes.get(queue.get().getDescription());
                    gameModeData.count++;
                    gameModeData.wins += participant.isWin() ? 1 : 0;
                    gameModeData.losses += participant.isWin() ? 0 : 1;
                    gameModeData.kills += participant.getKills();
                    gameModeData.deaths += participant.getDeaths();
                    gameModeData.assists += participant.getAssists();
                }
                gameModes.put(queue.get().getDescription(), gameModeData);
            } else {
                throw new NoSuchElementException("Erreur : La file de jeu " + metadata.getQueueId() + " n'existe pas.");
            }
        }
        // Calcul des statistiques
        for (GameModeData gameModeData : gameModes.values()) {
            gameModeData.winRate = ((float) gameModeData.wins / gameModeData.count);
            if (gameModeData.deaths == 0) {
                gameModeData.kda = Integer.MAX_VALUE;
            } else {
                gameModeData.kda = ((float) (gameModeData.kills + gameModeData.assists) / gameModeData.deaths);
            }
        }
        // Création de l'objet de retour
        // Summoner fields
        gameModesPlayed.setSummonerId(matches.get(0).getParticipants().get(participantId).getSummonerId());
        gameModesPlayed.setSummonerName(summonerName);

        gameModesPlayed.setTotalGamesPlayed(matches.size());
        gameModesPlayed.setTotalWins(gameModes.values().stream().mapToInt(gameModeData -> gameModeData.wins).sum());
        gameModesPlayed.setTotalLosses(gameModes.values().stream().mapToInt(gameModeData -> gameModeData.losses).sum());
        gameModesPlayed.setWinRate((float) gameModesPlayed.getTotalWins() / gameModesPlayed.getTotalGamesPlayed());

        gameModesPlayed.setTotalKills(gameModes.values().stream().mapToInt(gameModeData -> gameModeData.kills).sum());
        gameModesPlayed.setTotalDeaths(gameModes.values().stream().mapToInt(gameModeData -> gameModeData.deaths).sum());
        gameModesPlayed.setTotalAssists(gameModes.values().stream().mapToInt(gameModeData -> gameModeData.assists).sum());
        if (gameModesPlayed.getTotalDeaths() == 0) {
            gameModesPlayed.setKDA(Integer.MAX_VALUE);
        } else {
            gameModesPlayed.setKDA((float) (gameModesPlayed.getTotalKills() + gameModesPlayed.getTotalAssists()) / gameModesPlayed.getTotalDeaths());
        }

        // Most Played Champion fields
        GameModeData mostPlayedGameMode = Collections.max(gameModes.values(), Comparator.comparing(GameModeData::getCount));
        gameModesPlayed.setMostPlayedGameModeId(mostPlayedGameMode.getId());
        gameModesPlayed.setMostPlayedGameModeName(mostPlayedGameMode.getName());

        gameModesPlayed.setMostPlayedGameModeCount(mostPlayedGameMode.getCount());
        gameModesPlayed.setMostPlayedGameModeWins(mostPlayedGameMode.getWins());
        gameModesPlayed.setMostPlayedGameModeLosses(mostPlayedGameMode.getLosses());
        gameModesPlayed.setMostPlayedGameModeWinRate(mostPlayedGameMode.getWinRate());

        gameModesPlayed.setMostPlayedGameModeKills(mostPlayedGameMode.getKills());
        gameModesPlayed.setMostPlayedGameModeDeaths(mostPlayedGameMode.getDeaths());
        gameModesPlayed.setMostPlayedGameModeAssists(mostPlayedGameMode.getAssists());
        gameModesPlayed.setMostPlayedGameModeKDA(mostPlayedGameMode.getKda());

        // Best Performing Champion fields
        GameModeData bestPerformingGameMode = Collections.max(gameModes.values(), Comparator.comparing(GameModeData::getWinRate));
        gameModesPlayed.setBestPerformingGameModeId(bestPerformingGameMode.getId());
        gameModesPlayed.setBestPerformingGameModeName(bestPerformingGameMode.getName());

        gameModesPlayed.setBestPerformingGameModeCount(bestPerformingGameMode.getCount());
        gameModesPlayed.setBestPerformingGameModeWins(bestPerformingGameMode.getWins());
        gameModesPlayed.setBestPerformingGameModeLosses(bestPerformingGameMode.getLosses());
        gameModesPlayed.setBestPerformingGameModeWinRate(bestPerformingGameMode.getWinRate());

        gameModesPlayed.setBestPerformingGameModeKills(bestPerformingGameMode.getKills());
        gameModesPlayed.setBestPerformingGameModeDeaths(bestPerformingGameMode.getDeaths());
        gameModesPlayed.setBestPerformingGameModeAssists(bestPerformingGameMode.getAssists());
        gameModesPlayed.setBestPerformingGameModeKDA(bestPerformingGameMode.getKda());

        // Worst Performing Champion fields
        GameModeData worstPerformingGameMode = Collections.min(gameModes.values(), Comparator.comparing(GameModeData::getKda));
        gameModesPlayed.setWorstPerformingGameModeId(worstPerformingGameMode.getId());
        gameModesPlayed.setWorstPerformingGameModeName(worstPerformingGameMode.getName());

        gameModesPlayed.setWorstPerformingGameModeCount(worstPerformingGameMode.getCount());
        gameModesPlayed.setWorstPerformingGameModeWins(worstPerformingGameMode.getWins());
        gameModesPlayed.setWorstPerformingGameModeLosses(worstPerformingGameMode.getLosses());
        gameModesPlayed.setWorstPerformingGameModeWinRate(worstPerformingGameMode.getWinRate());

        gameModesPlayed.setWorstPerformingGameModeKills(worstPerformingGameMode.getKills());
        gameModesPlayed.setWorstPerformingGameModeDeaths(worstPerformingGameMode.getDeaths());
        gameModesPlayed.setWorstPerformingGameModeAssists(worstPerformingGameMode.getAssists());
        gameModesPlayed.setWorstPerformingGameModeKDA(worstPerformingGameMode.getKda());

        // Second Most Played Champion fields
        gameModes.remove(Collections.max(gameModes.values(), Comparator.comparing(GameModeData::getCount)).getName());
        GameModeData secondMostPlayedGameMode = Collections.max(gameModes.values(), Comparator.comparing(GameModeData::getCount));
        gameModesPlayed.setSecondMostPlayedGameModeId(secondMostPlayedGameMode.getId());
        gameModesPlayed.setSecondMostPlayedGameModeName(secondMostPlayedGameMode.getName());

        gameModesPlayed.setSecondMostPlayedGameModeCount(secondMostPlayedGameMode.getCount());
        gameModesPlayed.setSecondMostPlayedGameModeWins(secondMostPlayedGameMode.getWins());
        gameModesPlayed.setSecondMostPlayedGameModeLosses(secondMostPlayedGameMode.getLosses());
        gameModesPlayed.setSecondMostPlayedGameModeWinRate(secondMostPlayedGameMode.getWinRate());

        gameModesPlayed.setSecondMostPlayedGameModeKills(secondMostPlayedGameMode.getKills());
        gameModesPlayed.setSecondMostPlayedGameModeDeaths(secondMostPlayedGameMode.getDeaths());
        gameModesPlayed.setSecondMostPlayedGameModeAssists(secondMostPlayedGameMode.getAssists());
        gameModesPlayed.setSecondMostPlayedGameModeKDA(secondMostPlayedGameMode.getKda());

        return gameModesPlayed;
    }

    /*
     *******************************************************************************************************************
     ************************************************* PARTIE STATIQUE *************************************************
     *******************************************************************************************************************
     */

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
