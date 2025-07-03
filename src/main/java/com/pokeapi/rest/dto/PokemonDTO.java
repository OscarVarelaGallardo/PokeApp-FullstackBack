package com.pokeapi.rest.dto;

import java.util.List;
import java.util.Map;

public class PokemonDTO {
    private String name;
    private List<String> types;
    private List<String> abilities;
    private List<String> moves;
    private Map<String, Integer> stats;
    private String imageBase64;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getTypes() { return types; }
    public void setTypes(List<String> types) { this.types = types; }

    public List<String> getAbilities() { return abilities; }
    public void setAbilities(List<String> abilities) { this.abilities = abilities; }

    public List<String> getMoves() { return moves; }
    public void setMoves(List<String> moves) { this.moves = moves; }

    public Map<String, Integer> getStats() { return stats; }
    public void setStats(Map<String, Integer> stats) { this.stats = stats; }

    public String getImageBase64() { return imageBase64; }
    public void setImageBase64(String imageBase64) { this.imageBase64 = imageBase64; }
}