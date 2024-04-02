package com.project.ase_project.controller;

import com.project.ase_project.model.ddragon.champion.Champion;
import com.project.ase_project.service.ChampionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/riot")
public class ChampionController {

    @Autowired
    private ChampionService service;
    @GetMapping("/champions")
    @Tag(name = "Champions", description = "Methods for Champion APIs")
    @Operation(summary = "Get the list of all champions", description = "Get the list of all champions" +
            " as of patch 14.5.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resource", content =
                @Content(mediaType = "application/json", examples =
                @ExampleObject(value = "[{\"id\":\"Jax\",\"name\":\"Jax\",\"image\":{\"sprite\":\"champion1.png\",\"x\":432,\"y\":48,\"w\":48,\"h\":48,\"full\":\"Jax.png\",\"group\":\"champion\"},\"key\":24},{\"id\":\"Sona\",\"name\":\"Sona\",\"image\":{\"sprite\":\"champion4.png\",\"x\":192,\"y\":0,\"w\":48,\"h\":48,\"full\":\"Sona.png\",\"group\":\"champion\"},\"key\":37}]"))),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(examples = @ExampleObject()))})
    public List<Champion> findAllChampions(){
        return service.getChampions();
    }
    @GetMapping("/champions/{key}")
    @Tag(name = "Champions")
    @Operation(summary = "Get a champion's info", description = "Get a champion's info given by key, as of patch 14.5.1: " +
            "id, name, image information and key.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved resource",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"id\":\"Jax\",\"name\":\"Jax\",\"image\":{\"sprite\":\"champion1.png\",\"x\":432,\"y\":48,\"w\":48,\"h\":48,\"full\":\"Jax.png\",\"group\":\"champion\", \"key\":24}}"))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(examples = @ExampleObject()))})
    public Champion findChampionById(@PathVariable Integer key){
        Champion res = service.getChampionById(key);
        if (res == null){
            throw new IllegalArgumentException("Erreur 400 : l'id n'est pas correct.");
        } else {
            return res;
        }
    }

    @GetMapping("/champion/")
    @Operation(hidden=true)
    public Champion findChampionByIdException(){
        throw new IllegalArgumentException("Erreur 400 : Veuillez préciser l'id d'un champion.");
    }

}
