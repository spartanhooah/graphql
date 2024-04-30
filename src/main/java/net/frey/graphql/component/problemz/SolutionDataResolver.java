package net.frey.graphql.component.problemz;

import static net.frey.graphql.generated.DgsConstants.MUTATION_TYPE;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import net.frey.graphql.generated.DgsConstants;
import net.frey.graphql.generated.DgsConstants.MUTATION;
import net.frey.graphql.generated.types.Solution;
import net.frey.graphql.generated.types.SolutionCreateInput;
import net.frey.graphql.generated.types.SolutionResponse;
import net.frey.graphql.generated.types.SolutionVoteInput;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

@DgsComponent
public class SolutionDataResolver {
    @DgsData(parentType = MUTATION_TYPE, field = MUTATION.SolutionCreate)
    public SolutionResponse createSolution(
            @RequestHeader(name = "authToken") String authToken,
            @InputArgument(name = "newSolution") SolutionCreateInput solution) {
        return null;
    }

    @DgsData(parentType = MUTATION_TYPE, field = MUTATION.SolutionVote)
    public SolutionResponse voteOnSolution(
            @RequestHeader(name = "authToken") String authToken,
            @InputArgument(name = "newVote") SolutionVoteInput vote) {
        return null;
    }

    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE, field = DgsConstants.SUBSCRIPTION.SolutionVoteChanged)
    public Flux<Solution> voteSubscription(@InputArgument(name = "solutionId") String solutionId) {
        return null;
    }
}
