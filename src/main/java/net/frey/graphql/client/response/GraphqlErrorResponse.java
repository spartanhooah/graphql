package net.frey.graphql.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GraphqlErrorResponse(String message, List<String> path, List<Location> locations) {
    public record Location(int line, int column) {}
}
