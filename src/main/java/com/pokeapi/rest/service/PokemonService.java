package com.pokeapi.rest.service;

import com.pokeapi.rest.dto.PokemonDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Base64;

@Service
public class PokemonService {

    private final String API_URL = "https://pokeapi.co/api/v2/pokemon/";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    public PokemonDTO getPokemon(String name) throws Exception {
        String url = API_URL + name.toLowerCase();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        JsonNode root = mapper.readTree(response.getBody());

        PokemonDTO dto = new PokemonDTO();

        dto.setName(root.get("name").asText());

        dto.setTypes(root.get("types")
                .findValues("type")
                .stream()
                .map(t -> t.get("name").asText())
                .collect(Collectors.toList())
        );

        dto.setAbilities(root.get("abilities")
                .findValues("ability")
                .stream()
                .map(a -> a.get("name").asText())
                .collect(Collectors.toList())
        );

        dto.setMoves(root.get("moves")
                .findValues("move")
                .stream()
                .limit(5)
                .map(m -> m.get("name").asText())
                .collect(Collectors.toList())
        );

        Map<String, Integer> stats = new HashMap<>();
        for (JsonNode statNode : root.get("stats")) {
            String statName = statNode.get("stat").get("name").asText();
            int value = statNode.get("base_stat").asInt();
            stats.put(statName, value);
        }
        dto.setStats(stats);

        String imageUrl = root.get("sprites").get("front_default").asText();
        byte[] imageBytes = new URL(imageUrl).openStream().readAllBytes();
        String base64 = Base64.getEncoder().encodeToString(imageBytes);
        dto.setImageBase64("data:image/png;base64," + base64);

        return dto;
    }
}
