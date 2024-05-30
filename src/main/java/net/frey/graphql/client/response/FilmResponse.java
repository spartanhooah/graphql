package net.frey.graphql.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FilmResponse(String title, String director, String releaseDate) {}
