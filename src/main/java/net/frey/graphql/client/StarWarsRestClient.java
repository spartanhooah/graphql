package net.frey.graphql.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import net.frey.graphql.client.request.GraphqlRestRequest;
import net.frey.graphql.client.response.FilmResponse;
import net.frey.graphql.client.response.GraphqlErrorResponse;
import net.frey.graphql.client.response.PlanetResponse;
import net.frey.graphql.client.response.StarshipResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class StarWarsRestClient {
    private static final String URL = "https://swapi-graphql.netlify.app/.netlify/functions/index";

    private final RestClient client;
    private final ObjectMapper mapper;

    @Autowired
    public StarWarsRestClient(RestClient.Builder clientBuilder, ObjectMapper mapper) {
        this.mapper = mapper;
        client = clientBuilder.build();
    }

    public String asJson(GraphqlRestRequest body, Map<String, List<String>> headers) {
        var requestHeaders = new HttpHeaders();

        if (headers != null) {
            headers.forEach(requestHeaders::addAll);
        }

        return client.post()
                .uri(URL)
                .headers(realHeaders -> realHeaders.addAll(requestHeaders))
                .body(body)
                .retrieve()
                .body(String.class);
    }

    public List<PlanetResponse> getAllPlanets() throws JsonProcessingException {
        var query =
                """
                query allPlanets {
                  allPlanets {
                    planets {
                      name
                      climates
                      terrains
                    }
                  }
                }
            """;

        var body = new GraphqlRestRequest(query, null);

        var json = asJson(body, null);
        var jsonNode = mapper.readTree(json);
        var data = jsonNode.at("/data/allPlanets/planets");

        return mapper.readValue(data.toString(), new TypeReference<>() {});
    }

    public StarshipResponse oneStarshipFixed() throws JsonProcessingException {
        var query =
                """
                query oneStarshipFixed {
                  starship(id: "c3RhcnNoaXBzOjU=") {
                    model
                    name
                    manufacturers
                  }
                }
            """;

        var body = new GraphqlRestRequest(query, null);
        var json = asJson(body, null);
        var jsonNode = mapper.readTree(json);
        var data = jsonNode.at("/data/starship");

        return mapper.readValue(data.toString(), StarshipResponse.class);
    }

    public FilmResponse oneFilm(String filmId) throws JsonProcessingException {
        var query =
                """
                query oneFilm($filmId: ID!) {
                  film(filmID: $filmId) {
                    title
                    director
                    releaseDate
                  }
                }
            """;

        var body = new GraphqlRestRequest(query, Map.of("filmId", filmId));
        var json = asJson(body, null);

        var jsonNode = mapper.readTree(json);
        var data = jsonNode.at("/data/film");

        return mapper.readValue(data.toString(), FilmResponse.class);
    }

    public List<GraphqlErrorResponse> oneFilmInvalid() throws JsonProcessingException {
        var query =
                """
                query oneFilm($filmId: ID!) {
                  film(filmID: $filmId) {
                    title
                    director
                    releaseDate
                  }
                }
            """;

        var body = new GraphqlRestRequest(query, Map.of("filmId", "xxxx"));
        var json = asJson(body, null);

        var jsonNode = mapper.readTree(json);
        var errors = jsonNode.at("/errors");

        if (errors != null) {
            return mapper.readValue(errors.toString(), new TypeReference<>() {});
        }

        return null;
    }
}
