package com.pokeapi.rest.controller;

import com.pokeapi.rest.dto.PokemonDTO;
import com.pokeapi.rest.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/{name}")
    public PokemonDTO getPokemon(@PathVariable String name) throws Exception {
        return pokemonService.getPokemon(name);
    }
}