package net.frey.graphql.component.problemz;

import static net.frey.graphql.generated.DgsConstants.QUERY_TYPE;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.frey.graphql.generated.DgsConstants;
import net.frey.graphql.generated.DgsConstants.QUERY;
import net.frey.graphql.generated.types.Problem;
import net.frey.graphql.generated.types.ProblemCreateInput;
import net.frey.graphql.generated.types.ProblemResponse;
import net.frey.graphql.mapping.GraphqlBeanMapper;
import net.frey.graphql.service.query.ProblemzQueryService;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

@DgsComponent
@RequiredArgsConstructor
public class ProblemDataResolver {
    private final ProblemzQueryService queryService;

    @DgsData(parentType = QUERY_TYPE, field = QUERY.LatestProblems)
    public List<Problem> getLatestProblems() {
        return queryService.latestProblemz().stream()
                .map(GraphqlBeanMapper::mapToGraphql)
                .toList();
    }

    @DgsData(parentType = QUERY_TYPE, field = QUERY.ProblemById)
    public Problem getProblemDetail(@InputArgument(name = "id") String problemId) {
        return null;
    }

    @DgsData(parentType = DgsConstants.MUTATION_TYPE, field = DgsConstants.MUTATION.ProblemCreate)
    public ProblemResponse createProblem(
            @RequestHeader(name = "authToken") String authToken,
            @InputArgument(name = "problem") ProblemCreateInput problem) {
        return null;
    }

    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE, field = DgsConstants.SUBSCRIPTION.ProblemAdded)
    public Flux<Problem> problemSubscription() {
        return null;
    }
}
