package net.frey.graphql.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PlanetResponse(String name, List<String> climates, List<String> terrains) {}
