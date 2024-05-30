package net.frey.graphql.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StarshipResponse(String model, String name, List<String> manufacturers) {}
