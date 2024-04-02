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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resource",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"name\":\"Belugafurtif\",\"profileIconId\":6298,\"summonerLevel\":166,\"average\":3.625,\"cardinal\":48,\"puuid\":\"krLjP_t5lT5gcJqVMXPP7QWzTnUJa2gvskYSngZz8Oyr8o4-6_T-4-gjgteZLe8Dhz3md-OAcYVUdw\",\"id\":\"F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ\"}"))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "415", description = "Unsupported Media Type",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "429", description = "Too many requests",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "502", description = "Bad Gateway",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
    })
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resource",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "[{\"leagueId\":\"7c234c76-f6f5-4e06-aad3-2fa2fafd6951\",\"summonerId\":\"F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ\",\"summonerName\":\"Belugafurtif\",\"queueType\":\"RANKED_SOLO_5x5\",\"tier\":\"SILVER\",\"rank\":\"IV\",\"leaguePoints\":1,\"wins\":105,\"losses\":99},{\"leagueId\":\"13d1771a-99a8-4d56-ad04-5ced66978f76\",\"summonerId\":\"F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ\",\"summonerName\":\"Belugafurtif\",\"queueType\":\"RANKED_FLEX_SR\",\"tier\":\"GOLD\",\"rank\":\"IV\",\"leaguePoints\":28,\"wins\":4,\"losses\":5}]"))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "415", description = "Unsupported Media Type",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "429", description = "Too many requests",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "502", description = "Bad Gateway",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
    })
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resource",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"matchId\":\"EUW1_6760205418\",\"metadata\":{\"gameCreation\":1704900909290,\"gameDuration\":2171,\"gameEndTimestamp\":1704903099430,\"dataVersion\":\"2\",\"gameId\":6760205418,\"platformId\":\"EUW1\",\"gameMode\":\"CLASSIC\",\"gameName\":\"teambuilder-match-6760205418\",\"gameType\":\"MATCHED_GAME\",\"gameVersion\":\"14.1.552.7117\",\"mapId\":11,\"queueId\":400},\"participants\":[{\"puuid\":\"NSdcoRFNf4QK4MXv3t7k5DLJg3DBE6YpuGd2wIgmD57B3hYosKCeSePNoftPMcqRoXYqCnLycHTQvg\",\"summonerId\":\"Cnv9sKyA1mkOHb_cEX23LW0f9wIeceofEC5-iu5SIt9OJ1UPH5Gn7EAYsg\",\"summonerName\":\"020911 080623\",\"summonerLevel\":89,\"participantId\":1,\"riotIdGameName\":\"Forloven\",\"riotIdTagLine\":\"Link\",\"teamId\":100,\"role\":\"SOLO\",\"lane\":\"TOP\",\"teamPosition\":\"TOP\",\"individualPosition\":\"TOP\",\"championId\":23,\"championName\":\"Tryndamere\",\"win\":false,\"kills\":5,\"deaths\":9,\"assists\":1,\"visionScore\":24},{\"puuid\":\"W-6lu3K-72edepuy0jCAwrGtYt_8wCdKavdtq4p_6Hi6XZDeGAWoOns5WJ06_IJcY4qIam0EEvn2WA\",\"summonerId\":\"sCRgRqOCVUPBqHa8NPneL-8pjG-jdPURmBsrmXa68rAASAhGyg4vD6MAxw\",\"summonerName\":\"\",\"summonerLevel\":51,\"participantId\":2,\"riotIdGameName\":\"Joggeson\",\"riotIdTagLine\":\"4711\",\"teamId\":100,\"role\":\"NONE\",\"lane\":\"JUNGLE\",\"teamPosition\":\"JUNGLE\",\"individualPosition\":\"JUNGLE\",\"championId\":120,\"championName\":\"Hecarim\",\"win\":false,\"kills\":6,\"deaths\":6,\"assists\":7,\"visionScore\":14},{\"puuid\":\"VnbzIL6pFXWJxOoMxeaAJ4yOgigM0YC3VkY9KlGz2TcdFf67mVUf9Kg3JA42PHbzTrseflM_yu-FcQ\",\"summonerId\":\"J-ufPHbuxRnhq-t-RQ2aVzEb-yT0cANM0LmkqfomUf58ZLE\",\"summonerName\":\"Takumi D\",\"summonerLevel\":534,\"participantId\":3,\"riotIdGameName\":\"Takumi D\",\"riotIdTagLine\":\"0000\",\"teamId\":100,\"role\":\"SOLO\",\"lane\":\"MIDDLE\",\"teamPosition\":\"MIDDLE\",\"individualPosition\":\"MIDDLE\",\"championId\":3,\"championName\":\"Galio\",\"win\":false,\"kills\":12,\"deaths\":6,\"assists\":15,\"visionScore\":19},{\"puuid\":\"RPYtYTe4r08QikX2qNCALO5q3n9NU3d6FWeaNparmHEuCgcPaLtX-ag_uuM9lWBt2hLoU14OqcDJqA\",\"summonerId\":\"RLzpVRRhKRkOE5ehG7eY2u9QJl6dVNXu8U8vNeWK2nOmCIlG\",\"summonerName\":\"49 3 enjoyers\",\"summonerLevel\":280,\"participantId\":4,\"riotIdGameName\":\"49 3 enjoyers\",\"riotIdTagLine\":\"EUW\",\"teamId\":100,\"role\":\"CARRY\",\"lane\":\"BOTTOM\",\"teamPosition\":\"BOTTOM\",\"individualPosition\":\"BOTTOM\",\"championId\":96,\"championName\":\"KogMaw\",\"win\":false,\"kills\":16,\"deaths\":6,\"assists\":7,\"visionScore\":17},{\"puuid\":\"1MXlsiEgwnRYgQf3VmSvbxmEYZm3H_8-FGwa1he4WrWL_Uew-19s-gjqIX95yDcwbOR5gTvhFi7riw\",\"summonerId\":\"R2zJW-tMOSeILg5PbgRqS9bOdlhgAwli-JLUN1T5c40WATg\",\"summonerName\":\"DOLLEX ENJOYER\",\"summonerLevel\":556,\"participantId\":5,\"riotIdGameName\":\"DOLLEX ENJOYER\",\"riotIdTagLine\":\"EUW\",\"teamId\":100,\"role\":\"SUPPORT\",\"lane\":\"BOTTOM\",\"teamPosition\":\"UTILITY\",\"individualPosition\":\"UTILITY\",\"championId\":497,\"championName\":\"Rakan\",\"win\":false,\"kills\":1,\"deaths\":11,\"assists\":17,\"visionScore\":84},{\"puuid\":\"dxoUWrKBlQB9xP7tDH3nR4yF8FwOpBnMgXP-GIE6n80nHNuaw8ZTVDJxX8Xdw7ZE8d6c-9MnifVJrw\",\"summonerId\":\"fzni3JgU1zkhM2CsetTCLDRMVOsMm-Z6mx2mMgSwdFvvFZk\",\"summonerName\":\"inugami korone\",\"summonerLevel\":564,\"participantId\":6,\"riotIdGameName\":\"hatsune miku\",\"riotIdTagLine\":\"999\",\"teamId\":200,\"role\":\"SOLO\",\"lane\":\"TOP\",\"teamPosition\":\"TOP\",\"individualPosition\":\"TOP\",\"championId\":516,\"championName\":\"Ornn\",\"win\":true,\"kills\":1,\"deaths\":4,\"assists\":15,\"visionScore\":20},{\"puuid\":\"n0eI8uzwTTAg6dQgpQTQN-LLfdU3_SKHszG0sGs1UiJx1l611YAjlQGLr6HENdlC1ZZYQ9ZiTfGUzw\",\"summonerId\":\"aSaFsPEmtoVFz6SQzGIjsGoZHn4Otw67YxDWTE8bA84W-Wgl\",\"summonerName\":\"Not Simon\",\"summonerLevel\":341,\"participantId\":7,\"riotIdGameName\":\"Not Simon\",\"riotIdTagLine\":\"EUW\",\"teamId\":200,\"role\":\"NONE\",\"lane\":\"JUNGLE\",\"teamPosition\":\"JUNGLE\",\"individualPosition\":\"JUNGLE\",\"championId\":63,\"championName\":\"Brand\",\"win\":true,\"kills\":7,\"deaths\":10,\"assists\":8,\"visionScore\":40},{\"puuid\":\"6sHKShM4yhPnIMrhUHQjuWVk9gJFJmlgEguUYma7NQxX7KP0RSmsUWY13hq6IKaDa6P12x-2DV4p4w\",\"summonerId\":\"6mIria3VyGwD6uwjO_CmhcGSXqn5jpcc41KkaJ41-aHZDnFlRZasU5pGUQ\",\"summonerName\":\"aPoly\",\"summonerLevel\":408,\"participantId\":8,\"riotIdGameName\":\"Poli\",\"riotIdTagLine\":\"2469\",\"teamId\":200,\"role\":\"SOLO\",\"lane\":\"MIDDLE\",\"teamPosition\":\"MIDDLE\",\"individualPosition\":\"MIDDLE\",\"championId\":126,\"championName\":\"Jayce\",\"win\":true,\"kills\":9,\"deaths\":12,\"assists\":8,\"visionScore\":17},{\"puuid\":\"Gw-DlJXWkCliGKvzx7SxAltlyLYR3pSMN7qNmDFtE3qK9a1V3wXwltB8gxsb985Bw4WQtrefPYc6Cg\",\"summonerId\":\"-qzmNly6ihP4GwUnssjThDcodgKufnQNHvlvPO3hBTx9tyE\",\"summonerName\":\"Heyxler\",\"summonerLevel\":835,\"participantId\":9,\"riotIdGameName\":\"Heyxler\",\"riotIdTagLine\":\"187\",\"teamId\":200,\"role\":\"CARRY\",\"lane\":\"BOTTOM\",\"teamPosition\":\"BOTTOM\",\"individualPosition\":\"BOTTOM\",\"championId\":51,\"championName\":\"Caitlyn\",\"win\":true,\"kills\":10,\"deaths\":7,\"assists\":12,\"visionScore\":37},{\"puuid\":\"BUJmPYBwQjeaT-QBwg5v5FPdJh3rcoJaY7rjqq1PBuVNztGApND7ixjvwvg7d7GKvLUNtWoX3LDsbw\",\"summonerId\":\"ncNTafaHc1l5SDpWl3HFsTtT1PsSJvSQQvxjYkkEEc2Qst8\",\"summonerName\":\"Epic1ndi\",\"summonerLevel\":26,\"participantId\":10,\"riotIdGameName\":\"Epic1ndi\",\"riotIdTagLine\":\"EUW\",\"teamId\":200,\"role\":\"SUPPORT\",\"lane\":\"BOTTOM\",\"teamPosition\":\"UTILITY\",\"individualPosition\":\"UTILITY\",\"championId\":99,\"championName\":\"Lux\",\"win\":true,\"kills\":11,\"deaths\":7,\"assists\":12,\"visionScore\":69}],\"teams\":[{\"teamId\":100,\"win\":false,\"firstBaron\":true,\"baronKills\":1,\"firstChampion\":true,\"championKills\":40,\"firstDragon\":true,\"dragonKills\":2,\"firstHorde\":true,\"hordeKills\":4,\"firstInhibitor\":true,\"inhibitorKills\":1,\"firstRiftHerald\":true,\"riftHeraldKills\":1,\"firstTower\":false,\"towerKills\":8},{\"teamId\":200,\"win\":true,\"firstBaron\":false,\"baronKills\":0,\"firstChampion\":false,\"championKills\":38,\"firstDragon\":false,\"dragonKills\":2,\"firstHorde\":false,\"hordeKills\":2,\"firstInhibitor\":false,\"inhibitorKills\":1,\"firstRiftHerald\":false,\"riftHeraldKills\":0,\"firstTower\":true,\"towerKills\":9}],\"drafts\":[{\"teamId\":100,\"firstBan\":360,\"secondBan\":121,\"thirdBan\":53,\"fourthBan\":55,\"fifthBan\":24,\"firstPick\":23,\"secondPick\":120,\"thirdPick\":3,\"fourthPick\":96,\"fifthPick\":497},{\"teamId\":200,\"firstBan\":107,\"secondBan\":84,\"thirdBan\":-1,\"fourthBan\":81,\"fifthBan\":48,\"firstPick\":516,\"secondPick\":63,\"thirdPick\":126,\"fourthPick\":51,\"fifthPick\":99}]}"))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "415", description = "Unsupported Media Type",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "429", description = "Too many requests",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "502", description = "Bad Gateway",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
    })
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
    @Operation(summary = "Get a summoner's last 45 matches", description = "Get a summoner's last 45 matches given by an array of matchIds.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resource",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "[\"EUW1_6882272431\",\"EUW1_6882261316\",\"EUW1_6882191359\",\"EUW1_6882183404\",\"EUW1_6882143453\",\"EUW1_6881922121\",\"EUW1_6881839389\",\"EUW1_6881748481\",\"EUW1_6878820040\",\"EUW1_6878756124\",\"EUW1_6878286009\",\"EUW1_6877532999\",\"EUW1_6877465201\",\"EUW1_6877341348\",\"EUW1_6877252256\",\"EUW1_6877153147\",\"EUW1_6877091781\",\"EUW1_6877046649\",\"EUW1_6877028763\",\"EUW1_6876968373\",\"EUW1_6876112394\",\"EUW1_6876059510\",\"EUW1_6875965812\",\"EUW1_6872838235\",\"EUW1_6872532811\",\"EUW1_6872084925\",\"EUW1_6871916039\",\"EUW1_6871801469\",\"EUW1_6869808647\",\"EUW1_6869762516\",\"EUW1_6869634126\",\"EUW1_6869094176\",\"EUW1_6863136262\",\"EUW1_6863081021\",\"EUW1_6862929195\",\"EUW1_6862867676\",\"EUW1_6862805330\",\"EUW1_6862732265\",\"EUW1_6862601616\",\"EUW1_6862531476\",\"EUW1_6862471770\",\"EUW1_6862370702\",\"EUW1_6862323041\",\"EUW1_6862272372\",\"EUW1_6862198413\"]"))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "415", description = "Unsupported Media Type",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "429", description = "Too many requests",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "502", description = "Bad Gateway",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
    })
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resource",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"summonerId\":\"F4btU20wCQOmkMlWn4QJm33f3jH-B5Nj-uPfNnyuLED3PT0DpQ_LLcB_IQ\",\"summonerName\":\"Belugafurtif\",\"summonerLevel\":166,\"profileIconId\":6298,\"rankFlex\":\"GOLD IV 28 LP 4W / 5L\",\"rankSolo\":\"SILVER IV 1 LP 105W / 99L\",\"region\":\"EUW1\",\"average\":3.63,\"cardinal\":48,\"profileIconAddress\":\"https://ddragon.leagueoflegends.com/cdn/14.5.1/img/profileicon/6298.png\"}"))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "415", description = "Unsupported Media Type",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "429", description = "Too many requests",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "502", description = "Bad Gateway",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
    })
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully posted resource",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject())),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "415", description = "Unsupported Media Type",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "429", description = "Too many requests",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "502", description = "Bad Gateway",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
    })
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully posted resource",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject("{\"summonerId\":\"no3DrNwBLS6OfHYxUK1LtMqQrKcxixwr6zCWY9K0uveQIr2jpaNfyvrLgg\",\"summonerName\":\"Belugafurtif\",\"winRate\":0.55,\"kda\":2.3671875,\"totalGamesPlayed\":20,\"mostPlayedChampionId\":19,\"mostPlayedChampionName\":\"Warwick\",\"mostPlayedChampionLosses\":7,\"totalDeaths\":128,\"totalKills\":157,\"totalAssists\":146,\"totalWins\":11,\"totalLosses\":9,\"mostPlayedChampionCount\":17,\"mostPlayedChampionKills\":146,\"mostPlayedChampionKDA\":2.49,\"mostPlayedChampionWins\":10,\"bestPerformingChampionKDA\":1.82,\"worstPerformingChampionId\":102,\"worstPerformingChampionName\":\"Shyvana\",\"worstPerformingChampionCount\":2,\"worstPerformingChampionWinRate\":0.0,\"worstPerformingChampionKills\":4,\"bestPerformingChampionLosses\":0,\"bestPerformingChampionDeaths\":11,\"bestPerformingChampionAssists\":13,\"bestPerformingChampionCount\":1,\"mostPlayedChampionWinRate\":0.59,\"bestPerformingChampionId\":24,\"bestPerformingChampionKills\":7,\"worstPerformingChampionWins\":0,\"worstPerformingChampionLosses\":2,\"bestPerformingChampionName\":\"Jax\",\"bestPerformingChampionWins\":1,\"bestPerformingChampionWinRate\":1.0,\"mostPlayedChampionDeaths\":110,\"mostPlayedChampionAssists\":128,\"worstPerformingChampionAssists\":5,\"worstPerformingChampionKDA\":1.29,\"secondMostPlayedChampionId\":102,\"secondMostPlayedChampionCount\":2,\"secondMostPlayedChampionAssists\":5,\"secondMostPlayedChampionKills\":4,\"secondMostPlayedChampionDeaths\":7,\"secondMostPlayedChampionLosses\":2,\"secondMostPlayedChampionKDA\":1.29,\"secondMostPlayedChampionName\":\"Shyvana\",\"secondMostPlayedChampionWinRate\":0.0,\"secondMostPlayedChampionWins\":0,\"worstPerformingChampionDeaths\":7,\"mpprofileIconAddress\":\"https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/Warwick.png\",\"wpprofileIconAddress\":\"https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/Shyvana.png\",\"bpprofileIconAddress\":\"https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/Jax.png\",\"smpprofileIconAddress\":\"https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/Shyvana.png\"}"))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "415", description = "Unsupported Media Type",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "429", description = "Too many requests",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "502", description = "Bad Gateway",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
    })
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully posted resource",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject("{\"summonerId\":\"no3DrNwBLS6OfHYxUK1LtMqQrKcxixwr6zCWY9K0uveQIr2jpaNfyvrLgg\",\"summonerName\":\"Belugafurtif\",\"winRate\":0.55,\"kda\":2.3671875,\"totalGamesPlayed\":20,\"totalDeaths\":128,\"totalKills\":157,\"totalAssists\":146,\"totalWins\":11,\"totalLosses\":9,\"mostPlayedGameModeCount\":19,\"mostPlayedGameModeId\":420,\"mostPlayedGameModeName\":\"5v5 Ranked Solo games\",\"mostPlayedGameModeWins\":10,\"mostPlayedGameModeKills\":150,\"mostPlayedGameModeKDA\":2.41,\"bestPerformingGameModeName\":\"5v5 Draft Pick games\",\"bestPerformingGameModeCount\":1,\"mostPlayedGameModeLosses\":9,\"mostPlayedGameModeWinRate\":0.53,\"mostPlayedGameModeDeaths\":120,\"mostPlayedGameModeAssists\":139,\"bestPerformingGameModeId\":400,\"worstPerformingGameModeWins\":1,\"worstPerformingGameModeLosses\":0,\"worstPerformingGameModeWinRate\":1.0,\"worstPerformingGameModeId\":400,\"worstPerformingGameModeKills\":7,\"bestPerformingGameModeKills\":7,\"worstPerformingGameModeDeaths\":8,\"worstPerformingGameModeAssists\":7,\"worstPerformingGameModeKDA\":1.75,\"bestPerformingGameModeWins\":1,\"secondMostPlayedGameModeId\":400,\"bestPerformingGameModeDeaths\":8,\"secondMostPlayedGameModeName\":\"5v5 Draft Pick games\",\"secondMostPlayedGameModeCount\":1,\"bestPerformingGameModeWinRate\":1.0,\"bestPerformingGameModeKDA\":1.75,\"worstPerformingGameModeName\":\"5v5 Draft Pick games\",\"worstPerformingGameModeCount\":1,\"bestPerformingGameModeLosses\":0,\"bestPerformingGameModeAssists\":7,\"secondMostPlayedGameModeWins\":1,\"secondMostPlayedGameModeKills\":7,\"secondMostPlayedGameModeKDA\":1.75,\"secondMostPlayedGameModeLosses\":0,\"secondMostPlayedGameModeDeaths\":8,\"secondMostPlayedGameModeWinRate\":1.0,\"secondMostPlayedGameModeAssists\":7}"))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "404", description = "Resource Not Found",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "415", description = "Unsupported Media Type",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "429", description = "Too many requests",
                    content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "502", description = "Bad Gateway",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout",
                    content = @Content(examples = @ExampleObject())),@ApiResponse(responseCode = "429", description = "Too many requests",
            content = @Content(examples = @ExampleObject())),
    })
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
