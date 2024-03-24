package com.project.ase_project;

import com.project.ase_project.exception.*;
import com.project.ase_project.model.clean.league.League;
import com.project.ase_project.model.clean.match.Match;
import com.project.ase_project.model.clean.summary.Summary;
import com.project.ase_project.model.clean.summoner.Summoner;
import com.project.ase_project.service.RiotApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.project.ase_project.model.clean.match.Match;
import com.project.ase_project.model.clean.league.League;
import com.project.ase_project.model.clean.summoner.Summoner;
import com.project.ase_project.service.RiotApiService;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;


@RestController
@RequestMapping("/riot")
@SpringBootApplication
public class AseProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AseProjectApplication.class, args);
    }

    @Autowired
    private RiotApiService riotApiService;

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    // Exemple : http://localhost:8080/riot/summoners/Belugafurtif
    @GetMapping("/summoners/")
    public ResponseEntity<Summoner> getEmptySummonerData() {
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser un pseudo de joueur.");
    }
    
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
            model.addAttribute("summoner", summonerName);
            return "not_found";
        }
    }

    // Exemple : http://localhost:8080/riot/rank/F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ
    @GetMapping("/rank/")
    public ResponseEntity<ArrayList<League>> getEmptyRankData() {
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser un identifiant de joueur.");
    }

    @GetMapping("/rank/{encryptedSummonerId}")
    public ResponseEntity<ArrayList<League>> getRankData(@PathVariable String encryptedSummonerId) {
        ArrayList<League> rankList = riotApiService.getRankData(encryptedSummonerId);
        return new ResponseEntity<>(rankList, HttpStatus.OK);
    }

    // Exemple : http://localhost:8080/riot/matches/EUW1_6760205418
    @GetMapping("/matches/")
    public ResponseEntity<Match> getEmptyMatchData() {
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser un identifiant de partie.");
    }
    
    @GetMapping("/matches/{matchId}")
    public ResponseEntity<Match> getMatchData(@PathVariable String matchId) {
        Match match = riotApiService.getMatchById(matchId);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    // Exemple : http://localhost:8080/riot/summary/Belugafurtif
    @GetMapping("/summary/")
    public ResponseEntity<Summary> getEmptySummary() {
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser un pseudo de joueur.");
    }

    @GetMapping("/summary/{summonerName}")
    public ResponseEntity<Summary> getSummary(@PathVariable String summonerName) {
        if (summonerName == null) {
            throw new IllegalArgumentException("Erreur 400 : Veuillez préciser un pseudo de joueur.");
        } else {
            try {
                Summary summary = riotApiService.getSummary(summonerName);
                return new ResponseEntity<>(summary, HttpStatus.OK);
            } catch (BadRequestException e) {
                throw new BadRequestException(e.getMessage());
            } catch (SummonerNotFoundException e) {
                throw new SummonerNotFoundException(e.getMessage());
            } catch (LeaguesNotFoundException e) {
                throw new LeaguesNotFoundException(e.getMessage());
            } catch (MethodNotAllowed e) {
                throw new MethodNotAllowed(e.getMessage());
            } catch (UnsupportedMediaType e) {
                throw new UnsupportedMediaType(e.getMessage());
            } catch (RateLimitExceededException e) {
                throw new RateLimitExceededException(e.getMessage());
            } catch (InternalServerError e) {
                throw new InternalServerError(e.getMessage());
            } catch (BadGateway e) {
                throw new BadGateway(e.getMessage());
            } catch (ServiceUnavailable e) {
                throw new ServiceUnavailable(e.getMessage());
            } catch (GatewayTimeout e) {
                throw new GatewayTimeout(e.getMessage());
            }
        }
    }

    // Exemple : http://localhost:8080/riot/grade/Belugafurtif/5
    @PostMapping("/grade/")
    public ResponseEntity<String> postGradeNoSummoner() {
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser un pseudo de joueur.");
    }

    @PostMapping("/grade/{summonerName}")
    public ResponseEntity<String> postGradeNoGrade(@PathVariable String summonerName) {
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser une note entre 0 et 5 pour le joueur " + summonerName + ".");
    }

    @PostMapping("/grade/{summonerName}/{grade}")
    public ResponseEntity<String> getGrade(@PathVariable String summonerName, @PathVariable String grade) {
        if (summonerName == null) {
            throw new IllegalArgumentException("Erreur 400 : Veuillez préciser un pseudo de joueur.");
        } else {
            try {
                int note = Integer.parseInt(grade);
                if (note > 5 || note < 0) {
                    throw new IllegalArgumentException("Erreur 400 : Veuillez préciser une note entre 0 et 5.");
                }
                riotApiService.postGrade(summonerName, note);
                return new ResponseEntity<>("Votre note a été posté avec succès !", HttpStatus.OK);
            } catch (BadRequestException e) {
                throw new BadRequestException(e.getMessage());
            } catch (SummonerNotFoundException e) {
                throw new SummonerNotFoundException(e.getMessage());
            } catch (MethodNotAllowed e) {
                throw new MethodNotAllowed(e.getMessage());
            } catch (UnsupportedMediaType e) {
                throw new UnsupportedMediaType(e.getMessage());
            } catch (RateLimitExceededException e) {
                throw new RateLimitExceededException(e.getMessage());
            } catch (InternalServerError e) {
                throw new InternalServerError(e.getMessage());
            } catch (BadGateway e) {
                throw new BadGateway(e.getMessage());
            } catch (ServiceUnavailable e) {
                throw new ServiceUnavailable(e.getMessage());
            } catch (GatewayTimeout e) {
                throw new GatewayTimeout(e.getMessage());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Erreur 400 : Veuillez préciser une note valide.");
            }
        }
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBadRequest(BadRequestException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadGateway.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ResponseEntity<String> handleBadGateway(BadGateway e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(GatewayTimeout.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public ResponseEntity<String> handleGateTimeout(GatewayTimeout e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.GATEWAY_TIMEOUT);
    }

    @ExceptionHandler(SummonerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleSummonerNotFoundException(SummonerNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LeaguesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleLeaguesNotFoundException(LeaguesNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MatchNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleMatchNotFoundException(MatchNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodNotAllowed.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<String> handleMethodNotAllowed(MethodNotAllowed e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(UnsupportedMediaType.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResponseEntity<String> handleUnsupportedMediaType(UnsupportedMediaType e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(RateLimitExceededException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public ResponseEntity<String> handleRateLimitExceededException(RateLimitExceededException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(InternalServerError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleInternalServerError(InternalServerError e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceUnavailable.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<String> handleServiceUnavailable(ServiceUnavailable e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
