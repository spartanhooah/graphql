package net.frey.graphql.component.problemz;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.DgsSubscription;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.frey.graphql.exception.ProblemzAuthenticationException;
import net.frey.graphql.generated.types.Problem;
import net.frey.graphql.generated.types.ProblemCreateInput;
import net.frey.graphql.generated.types.ProblemResponse;
import net.frey.graphql.mapping.GraphqlBeanMapper;
import net.frey.graphql.service.command.ProblemzCommandService;
import net.frey.graphql.service.query.ProblemzQueryService;
import net.frey.graphql.service.query.UserzQueryService;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

@DgsComponent
@RequiredArgsConstructor
public class ProblemDataResolver {
    private final ProblemzQueryService problemzQueryService;
    private final ProblemzCommandService problemzCommandService;
    private final UserzQueryService userzQueryService;

    @DgsQuery(field = "latestProblems")
    public List<Problem> getLatestProblems() {
        return problemzQueryService.latestProblemz().stream()
                .map(GraphqlBeanMapper::mapToGraphql)
                .toList();
    }

    @DgsQuery(field = "problemById")
    public Problem getProblemById(@InputArgument(name = "id") String problemId) {
        return problemzQueryService
                .problemzById(UUID.fromString(problemId))
                .map(GraphqlBeanMapper::mapToGraphql)
                .orElseThrow(() -> new DgsEntityNotFoundException("No problem exists with ID " + problemId));
    }

    @DgsMutation(field = "problemCreate")
    public ProblemResponse createProblem(
            @RequestHeader(name = "authToken") String authToken,
            @InputArgument(name = "problem") ProblemCreateInput problem) {
        var userz = userzQueryService
                .findUserzByAuthToken(authToken)
                .orElseThrow(() -> new ProblemzAuthenticationException("User is not authenticated."));

        var problemz = GraphqlBeanMapper.mapToEntity(problem, userz);
        var created = problemzCommandService.createProblem(problemz);

        return ProblemResponse.newBuilder()
                .problem(GraphqlBeanMapper.mapToGraphql(created))
                .build();
    }

    @DgsSubscription(field = "problemAdded")
    public Flux<Problem> problemSubscription() {
        return null;
    }
}
