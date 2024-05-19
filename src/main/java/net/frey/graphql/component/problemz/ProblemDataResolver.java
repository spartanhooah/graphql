package net.frey.graphql.component.problemz;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.frey.graphql.generated.DgsConstants;
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

    @DgsQuery(field = "latestProblems")
    public List<Problem> getLatestProblems() {
        return queryService.latestProblemz().stream()
                .map(GraphqlBeanMapper::mapToGraphql)
                .toList();
    }

    @DgsQuery(field = "problemById")
    public Problem getProblemById(@InputArgument(name = "id") String problemId) {
        return queryService
                .problemzById(UUID.fromString(problemId))
                .map(GraphqlBeanMapper::mapToGraphql)
                .orElseThrow(() -> new IllegalArgumentException("No problem exists with ID" + problemId));
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
