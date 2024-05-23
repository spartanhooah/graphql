package net.frey.graphql.component.problemz;

import static java.util.UUID.fromString;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsSubscription;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.frey.graphql.datasource.problemz.entity.Solutionz;
import net.frey.graphql.exception.ProblemzAuthenticationException;
import net.frey.graphql.generated.types.Solution;
import net.frey.graphql.generated.types.SolutionCreateInput;
import net.frey.graphql.generated.types.SolutionResponse;
import net.frey.graphql.generated.types.SolutionVoteInput;
import net.frey.graphql.mapping.GraphqlBeanMapper;
import net.frey.graphql.service.command.SolutionzCommandService;
import net.frey.graphql.service.query.ProblemzQueryService;
import net.frey.graphql.service.query.UserzQueryService;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

@DgsComponent
@RequiredArgsConstructor
public class SolutionDataResolver {
    private final UserzQueryService userzQueryService;
    private final ProblemzQueryService problemzQueryService;
    private final SolutionzCommandService solutionzCommandService;

    @DgsMutation(field = "solutionCreate")
    public SolutionResponse createSolution(
            @RequestHeader(name = "authToken") String authToken,
            @InputArgument(name = "solution") SolutionCreateInput solution) {
        var userz = userzQueryService
                .findUserzByAuthToken(authToken)
                .orElseThrow(() -> new ProblemzAuthenticationException("Could not authenticate user"));

        var problemz = problemzQueryService
                .problemzById(fromString(solution.getProblemId()))
                .orElseThrow(DgsEntityNotFoundException::new);

        var solutionz = GraphqlBeanMapper.mapToEntity(solution, userz, problemz);
        var created = solutionzCommandService.createSolutionz(solutionz);

        return SolutionResponse.newBuilder()
                .solution(GraphqlBeanMapper.mapToGraphql(created))
                .build();
    }

    @DgsMutation(field = "solutionVote")
    public SolutionResponse voteOnSolution(
            @RequestHeader(name = "authToken") String authToken, @InputArgument(name = "vote") SolutionVoteInput vote) {
        Optional<Solutionz> updated;
        userzQueryService
                .findUserzByAuthToken(authToken)
                .orElseThrow(() -> new ProblemzAuthenticationException("Could not authenticate user"));

        UUID solutionId = fromString(vote.getSolutionId());
        if (vote.getIsGood()) {
            updated = solutionzCommandService.thumbsUp(solutionId);
        } else {
            updated = solutionzCommandService.thumbsDown(solutionId);
        }

        return updated.map(solutionz -> SolutionResponse.newBuilder()
                        .solution(GraphqlBeanMapper.mapToGraphql(solutionz))
                        .build())
                .orElseThrow(() -> new DgsEntityNotFoundException("Solution " + solutionId + " not found"));
    }

    @DgsSubscription(field = "solutionVoteChanged")
    public Flux<Solution> voteSubscription(@InputArgument(name = "solutionId") String solutionId) {
        return null;
    }
}
