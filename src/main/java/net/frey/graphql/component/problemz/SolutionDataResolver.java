package net.frey.graphql.component.problemz;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsSubscription;
import com.netflix.graphql.dgs.InputArgument;
import net.frey.graphql.generated.types.Solution;
import net.frey.graphql.generated.types.SolutionCreateInput;
import net.frey.graphql.generated.types.SolutionResponse;
import net.frey.graphql.generated.types.SolutionVoteInput;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

@DgsComponent
public class SolutionDataResolver {
    @DgsMutation(field = "solutionCreate")
    public SolutionResponse createSolution(
            @RequestHeader(name = "authToken") String authToken,
            @InputArgument(name = "solution") SolutionCreateInput solution) {
        return null;
    }

    @DgsMutation(field = "solutionVote")
    public SolutionResponse voteOnSolution(
            @RequestHeader(name = "authToken") String authToken, @InputArgument(name = "vote") SolutionVoteInput vote) {
        return null;
    }

    @DgsSubscription(field = "solutionVoteChanged")
    public Flux<Solution> voteSubscription(@InputArgument(name = "solutionId") String solutionId) {
        return null;
    }
}
