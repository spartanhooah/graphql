package net.frey.graphql.exception.handler;

import com.netflix.graphql.dgs.exceptions.DefaultDataFetcherExceptionHandler;
import com.netflix.graphql.types.errors.TypedGraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import java.util.concurrent.CompletableFuture;
import net.frey.graphql.exception.ProblemzAuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ProblemzGraphqlExceptionHandler implements DataFetcherExceptionHandler {
    private final DefaultDataFetcherExceptionHandler defaultHandler = new DefaultDataFetcherExceptionHandler();

    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(
            DataFetcherExceptionHandlerParameters handlerParameters) {
        var exception = handlerParameters.getException();

        if (exception instanceof ProblemzAuthenticationException) {
            var graphqlError = TypedGraphQLError.newBuilder()
                    .message(exception.getMessage())
                    .path(handlerParameters.getPath())
                    .errorType(new ProblemzErrorDetail())
                    .build();

            var result = DataFetcherExceptionHandlerResult.newResult()
                    .error(graphqlError)
                    .build();

            return CompletableFuture.completedFuture(result);
        }

        return defaultHandler.handleException(handlerParameters);
    }
}
