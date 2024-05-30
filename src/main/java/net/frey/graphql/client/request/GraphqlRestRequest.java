package net.frey.graphql.client.request;

import java.util.Map;

public record GraphqlRestRequest(String query, Map<String, ? extends Object> variables) {}
