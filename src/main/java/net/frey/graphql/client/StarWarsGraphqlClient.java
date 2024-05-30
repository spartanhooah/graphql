package net.frey.graphql.client;

import com.jayway.jsonpath.TypeRef;
import com.netflix.graphql.dgs.client.GraphQLError;
import com.netflix.graphql.dgs.client.GraphQLResponse;
import com.netflix.graphql.dgs.client.WebClientGraphQLClient;
import net.frey.graphql.client.response.FilmResponse;
import net.frey.graphql.client.response.PlanetResponse;
import net.frey.graphql.client.response.StarshipResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StarWarsGraphqlClient {
    private static final String URL = "https://swapi-graphql.netlify.app/.netlify/functions/index";
    private static final String QUERY = """
        query allPlanets {
          allPlanets {
            planets {
              name
              climates
              terrains
            }
          }
        }
        query oneStarshipFixed {
          starship(id: "c3RhcnNoaXBzOjU=") {
            model
            name
            manufacturers
          }
        }
        query oneFilm($filmId: ID!) {
          film(filmID: $filmId) {
            title
            director
            releaseDate
          }
        }
        """;

    private final WebClientGraphQLClient client;

    public StarWarsGraphqlClient(WebClient.Builder webClientBuilder) {
        client = new WebClientGraphQLClient(webClientBuilder.build());
    }

    public Mono<String> asJson(String operation, Map<String, Object> variables) {
        return getGraphqlResponse(operation, variables)
            .map(GraphQLResponse::getJson);
    }

    public Mono<List<PlanetResponse>> allPlanets() {
        return getGraphqlResponse("allPlanets", null)
            .map( response -> response.extractValueAsObject("allPlanets.planets", new TypeRef<>() {}));
    }

    public Mono<StarshipResponse> oneStarshipFixed() {
        return getGraphqlResponse("oneStarshipFixed", null)
            .map(response -> response.extractValueAsObject("starship", new TypeRef<>() {}));
    }

    public Mono<FilmResponse> oneFilm(String filmId) {
        return getGraphqlResponse("oneFilm", Map.of("filmId", filmId))
            .map(response -> response.extractValueAsObject("starship", FilmResponse.class));
    }

    public Mono<List<GraphQLError>> oneFilmInvalid() {
        return getGraphqlResponse("oneFilm", Map.of("filmId", "xxxx"))
            .map(response -> response.hasErrors() ? response.getErrors() : null);
    }

    private Mono<GraphQLResponse> getGraphqlResponse(String operation, Map<String, ?> variables) {
        if (variables == null) {
            variables = new HashMap<>();
        }

        return client.reactiveExecuteQuery(QUERY, variables, operation);
    }
}
