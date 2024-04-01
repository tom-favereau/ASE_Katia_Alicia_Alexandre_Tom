package com.project.ase_project;

import com.project.ase_project.exception.*;
import com.project.ase_project.model.clean.MostPlayedChampions.ChampionsPlayed;
import com.project.ase_project.model.clean.MostPlayedGameModes.GameModesPlayed;
import com.project.ase_project.model.clean.league.League;
import com.project.ase_project.model.clean.match.Match;
import com.project.ase_project.model.clean.summary.Summary;
import com.project.ase_project.model.clean.summoner.Summoner;
import com.project.ase_project.service.RiotApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;


@Controller
@RequestMapping("/riot")
@SpringBootApplication
public class AseProjectApplication {

    @Generated
    public static void main(String[] args) {
        SpringApplication.run(AseProjectApplication.class, args);
    }

    @Autowired
    private RiotApiService riotApiService;

    /*
     *******************************************************************************************************************
     *************************************************** PARTIE API ****************************************************
     *******************************************************************************************************************
     */

    // Exemple : http://localhost:8080/riot/summoners/Belugafurtif
    @GetMapping("/summoners/")
    public ResponseEntity<Summoner> getEmptySummonerData() {
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser un pseudo de joueur.");
    }

    @PostMapping("/summoners/{summonerName}")
    public ResponseEntity<Summoner> postSummonerData(@PathVariable String summonerName) {
        throw new MethodNotAllowed("Erreur 405 : La méthode POST n'est pas autorisée pour cette route. Utilisez la méthode GET pour obtenir les informations de " + summonerName + ".");
    }
    
    @GetMapping("/summoners/{summonerName}")
    @Tag(name = "Summoner", description = "Methods for Summoner APIs")
    @Operation(summary = "Get a summoner's info", description = "Get a summoner's in-game name, profile icon ID, level, rating, puuid, id and profile icon address.")
    @ResponseBody
    public ResponseEntity<Summoner> getSummonerData(@PathVariable String summonerName) {
        try {
            Summoner summoner = riotApiService.getSummonerByName(summonerName);
            return new ResponseEntity<>(summoner, HttpStatus.OK);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (SummonerNotFoundException e) {
            throw new SummonerNotFoundException(e.getMessage());
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

    // Exemple : http://localhost:8080/riot/rank/F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ
    @GetMapping("/rank/")
    public ResponseEntity<ArrayList<League>> getEmptyRankData() {
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser un identifiant de joueur.");
    }

    @PostMapping("/rank/{encryptedSummonerId}")
    public ResponseEntity<ArrayList<League>> postRankData(@PathVariable String encryptedSummonerId) {
        throw new MethodNotAllowed("Erreur 405 : La méthode POST n'est pas autorisée pour cette route. Utilisez la méthode GET pour obtenir les informations du joueur avec l'identifiant " + encryptedSummonerId + ".");
    }

    @GetMapping("/rank/{encryptedSummonerId}")
    @Tag(name = "Summoner")
    @Operation(summary = "Get a summoner's rank", description = "Get a summoner's rank through their puuid, an array of" +
            " played leagues during the season is returned.")
    @ResponseBody
    public ResponseEntity<ArrayList<League>> getRankData(@PathVariable String encryptedSummonerId) {
        try {
            ArrayList<League> rankList = riotApiService.getRankById(encryptedSummonerId);
            return new ResponseEntity<>(rankList, HttpStatus.OK);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (LeaguesNotFoundException e) {
            throw new LeaguesNotFoundException(e.getMessage());
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

    // Exemple : http://localhost:8080/riot/matches/EUW1_6760205418
    @GetMapping("/matches/")
    public ResponseEntity<Match> getEmptyMatchData() {
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser un identifiant de partie.");
    }

    @PostMapping("/matches/{matchId}")
    public ResponseEntity<Match> postMatchData(@PathVariable String matchId) {
        throw new MethodNotAllowed("Erreur 405 : La méthode POST n'est pas autorisée pour cette route. Utilisez la méthode GET pour obtenir les informations de la partie avec l'identifiant " + matchId + ".");
    }
    
    @GetMapping("/matches/{matchId}")
    @Tag(name = "Matches", description = "Methods for Match APIs")
    @Operation(summary = "Get a match by matchId", description = "Get a match's metadata and participants by ID.")
    @ResponseBody
    public ResponseEntity<Match> getMatchData(@PathVariable String matchId) {
        try {
            Match match = riotApiService.getMatchById(matchId);
            return new ResponseEntity<>(match, HttpStatus.OK);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (MatchNotFoundException e) {
            throw new MatchNotFoundException(e.getMessage());
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

    @GetMapping("/lastMatches/{summonerName}")
    @Tag(name = "Summoner")
    @Operation(summary = "Get a summoner's last 20 matches", description = "Get a summoner's last 20 matches given by an array of matchIds.")
    @ResponseBody
    public ResponseEntity<ArrayList<String>> getLastMatches(@PathVariable String summonerName) {
        ArrayList<Match> matchesFutures = riotApiService.getMatches(summonerName, null, null, null, null, null, 100);
        ArrayList<String> matchIds = new ArrayList<>();
        for (Match match : matchesFutures) {
            matchIds.add(match.getMatchId());
        }
        return new ResponseEntity<>(matchIds, HttpStatus.OK);
    }

    // Exemple : http://localhost:8080/riot/summary/Belugafurtif
    @GetMapping("/summary/")
    public ResponseEntity<Summary> getEmptySummaryData() {
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser un pseudo de joueur.");
    }

    @PostMapping("/summary/{summonerName}")
    public ResponseEntity<Summary> postSummaryData(@PathVariable String summonerName) {
        throw new MethodNotAllowed("Erreur 405 : La méthode POST n'est pas autorisée pour cette route. Utilisez la méthode GET pour obtenir les informations du joueur " + summonerName + ".");
    }

    @GetMapping("/summary/{summonerName}")
    @Tag(name = "Summoner")
    @Operation(summary = "Get a summoner's summary", description = "Get a summoner's summary: their basic info and ranks.")
    @ResponseBody
    public ResponseEntity<Summary> getSummaryData(@PathVariable String summonerName) {
        try {
            Summary summary = riotApiService.getSummaryByName(summonerName);
            return new ResponseEntity<>(summary, HttpStatus.OK);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (SummonerNotFoundException e) {
            throw new SummonerNotFoundException(e.getMessage());
        } catch (LeaguesNotFoundException e) {
            throw new LeaguesNotFoundException(e.getMessage());
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

    // Exemple : http://localhost:8080/riot/grade/Belugafurtif/5
    @PostMapping("/grade/")
    public ResponseEntity<String> postGradeNoSummoner() {
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser un pseudo de joueur.");
    }

    @PostMapping("/grade/{summonerName}")
    public ResponseEntity<String> postGradeNoGrade(@PathVariable String summonerName) {
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser une note entre 0 et 5 pour le joueur " + summonerName + ".");
    }

    @GetMapping("/grade/{summonerName}")
    public ResponseEntity<String> getGradeNoGrade(@PathVariable String summonerName) {
        throw new IllegalArgumentException("Erreur 404 : Si vous voulez obtenir la note du joueur " + summonerName
                + ", utilisez la méthode GET sur l'URI riot/summoners/" + summonerName + " ou riot/summary/"
                + summonerName + ". Pour noter le joueur, utiliser la méthode POST sur /grade/"+ summonerName + "/{note}.");
    }

    @GetMapping("/grade/{summonerName}/{grade}")
    public ResponseEntity<String> getGradeData(@PathVariable String summonerName, @PathVariable String grade) {
        throw new MethodNotAllowed("Erreur 405 : La méthode GET n'est pas autorisée pour cette route. Utilisez la méthode POST pour donner au joueur " + summonerName + " la note " + grade + ".");
    }

    @PostMapping("/grade/{summonerName}/{grade}")
    @Tag(name = "Summoner")
    @Operation(summary = "Post a rating for a summoner", description = "Post a rating for a given summoner by name. The" +
            " grade is the interval [1, 5].")
    @ResponseBody
    public ResponseEntity<String> postGradeData(@PathVariable String summonerName, @PathVariable String grade) {
        try {
            int note = Integer.parseInt(grade);
            if (note > 5 || note < 1) {
                throw new IllegalArgumentException("Erreur 400 : Veuillez préciser une note entre 0 et 5.");
            }
            riotApiService.postGrade(summonerName, note);
            return new ResponseEntity<>("Votre note a été postée avec succès !", HttpStatus.OK);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (SummonerNotFoundException e) {
            throw new SummonerNotFoundException(e.getMessage());
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

    // Exemple : http://localhost:8080/riot/championsPlayed/Belugafurtif
    @GetMapping("/championsPlayed/")
    public ResponseEntity<ChampionsPlayed> getEmptyChampionsPlayed() {
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser un pseudo de joueur.");
    }

    @PostMapping("/championsPlayed/{summonerName}")
    public ResponseEntity<ChampionsPlayed> postChampionsPlayed(@PathVariable String summonerName) {
        throw new MethodNotAllowed("Erreur 405 : La méthode POST n'est pas autorisée pour cette route. Utilisez la méthode GET pour obtenir les champions joués par " + summonerName + ".");
    }

    @GetMapping("/championsPlayed/{summonerName}")
    @Tag(name = "Summoner")
    @Operation(summary = "Get information about a summoner's most played champions and best champions", description = "Get the name and " +
            "associated statistics of the most played, second-most played, best-performing and worst-performing champions" +
            " by summoner given by their name.")
    @ResponseBody
    public ResponseEntity<ChampionsPlayed> getChampionsPlayed(@PathVariable String summonerName) {
        try {
            ChampionsPlayed championsPlayed = riotApiService.getChampionsPlayedByName(summonerName);
            return new ResponseEntity<>(championsPlayed, HttpStatus.OK);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (SummonerNotFoundException e) {
            throw new SummonerNotFoundException(e.getMessage());
        } catch (MatchNotFoundException e) {
            throw new MatchNotFoundException(e.getMessage());
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

    // Exemple : http://localhost:8080/riot/gameModesPlayed/Belugafurtif
    @GetMapping("/gameModesPlayed/")
    public ResponseEntity<GameModesPlayed> getEmptyGameModesPlayed() {
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser un pseudo de joueur.");
    }

    @PostMapping("/gameModesPlayed/{summonerName}")
    public ResponseEntity<GameModesPlayed> postGameModesPlayed(@PathVariable String summonerName) {
        throw new MethodNotAllowed("Erreur 405 : La méthode POST n'est pas autorisée pour cette route. Utilisez la méthode GET pour obtenir les modes de jeu joués par " + summonerName + ".");
    }

    @GetMapping("/gameModesPlayed/{summonerName}")
    @Tag(name = "Summoner")
    @Operation(summary = "Get information about a summoner's most played game modes and best game modes",
            description = "Get the name and associated statistics of the most played, second-most played, " +
                    "best-performing and worst-performing game modes by summoner given by their name.")
    @ResponseBody
    public ResponseEntity<GameModesPlayed> getGameModesPlayed(@PathVariable String summonerName) {
        try {
            GameModesPlayed gameModesPlayed = riotApiService.getGameModesPlayedByName(summonerName);
            return new ResponseEntity<>(gameModesPlayed, HttpStatus.OK);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        } catch (SummonerNotFoundException e) {
            throw new SummonerNotFoundException(e.getMessage());
        } catch (MatchNotFoundException e) {
            throw new MatchNotFoundException(e.getMessage());
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(e.getMessage());
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

    /*
     *******************************************************************************************************************
     ************************************************* PARTIE FRONTEND *************************************************
     *******************************************************************************************************************
     */


    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/summoner_page/{summonerName}")
    public String getSummonerData(@PathVariable String summonerName, Model model) {
        try {
            Summary summary = riotApiService.getSummaryByName(summonerName);
            model.addAttribute("summoner", summary);

            ChampionsPlayed championsPlayed = riotApiService.getChampionsPlayedByName(summonerName);
            model.addAttribute("championsPlayed", championsPlayed);

            GameModesPlayed gameModesPlayed = riotApiService.getGameModesPlayedByName(summonerName);
            model.addAttribute("gameModesPlayed", gameModesPlayed);

            return "summoner";
        }
        catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("summonerName", summonerName);
            return "not_found";
        }
    }

    @RequestMapping("favicon.ico")
    String favicon() {
        return "forward:/favicon.ico";
    }

    /*
     *******************************************************************************************************************
     ******************************************** PARTIE EXCEPTIONS HANDLER ********************************************
     *******************************************************************************************************************
     */

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
