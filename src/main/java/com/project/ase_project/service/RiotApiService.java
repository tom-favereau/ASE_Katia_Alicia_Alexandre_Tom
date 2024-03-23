package com.project.ase_project.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.project.ase_project.model.dto.summoner.SummonerDto;
import com.project.ase_project.model.dto.match.MatchDto;
import com.project.ase_project.model.dto.league.LeagueDto;

import com.project.ase_project.exception.*;

import com.project.ase_project.model.clean.summary.Summary;
import com.project.ase_project.model.clean.league.League;
import com.project.ase_project.model.clean.match.Match;
import com.project.ase_project.model.clean.summoner.Summoner;

import com.project.ase_project.repository.MatchRepository;

@Service
public class RiotApiService {

    @Value("${riot.api.key}")
    private String apiKey;

    private final MatchRepository matchRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public RiotApiService(RestTemplate restTemplate, MatchRepository matchRepository) {
        this.restTemplate = restTemplate;
        this.matchRepository = matchRepository;
    }

    public Summoner getSummonerByName(String summonerName) {
        String apiUrl = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key="+apiKey;
        try {
            SummonerDto summonerDto = restTemplate.getForObject(apiUrl, SummonerDto.class);
            if (summonerDto != null) {
                return summonerDto.toSummoner();
            }
            else {
                System.out.println("Summoner not found");
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
            if (leaguesDto != null && leaguesDto.length > 0) {
                ArrayList<League> leagues = new ArrayList<>();
                for (LeagueDto leagueDto : leaguesDto) {
                    League league = leagueDto.toLeague();
                    leagues.add(league);
                }
                return leagues;
            }
            else {
                throw new LeaguesNotFoundException("Erreur 404 : Le joueur avec l'identifiant " + encryptedSummonerId + " n'a pas de classement.");
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
}
