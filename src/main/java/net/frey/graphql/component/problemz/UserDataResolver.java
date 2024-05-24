package net.frey.graphql.component.problemz;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;
import net.frey.graphql.exception.ProblemzAuthenticationException;
import net.frey.graphql.generated.types.*;
import net.frey.graphql.mapping.GraphqlBeanMapper;
import net.frey.graphql.service.command.UserzCommandService;
import net.frey.graphql.service.query.UserzQueryService;
import org.springframework.web.bind.annotation.RequestHeader;

@DgsComponent
@RequiredArgsConstructor
public class UserDataResolver {
    private final UserzCommandService commandService;
    private final UserzQueryService queryService;

    @DgsQuery(field = "me")
    public User accountInfo(@RequestHeader(name = "authToken") String authToken) {
        return queryService
                .findUserzByAuthToken(authToken)
                .map(GraphqlBeanMapper::mapToGraphql)
                .orElseThrow(() -> new ProblemzAuthenticationException("User auth token not found"));
    }

    @DgsMutation(field = "userCreate")
    public UserResponse createUser(@InputArgument(name = "user") UserCreateInput userCreateInput) {
        var userz = GraphqlBeanMapper.mapToEntity(userCreateInput);
        var saved = commandService.createUserz(userz);

        return UserResponse.newBuilder()
                .user(GraphqlBeanMapper.mapToGraphql(saved))
                .build();
    }

    @DgsMutation
    public UserResponse userLogin(@InputArgument(name = "user") UserLoginInput userLoginInput) {
        var token = GraphqlBeanMapper.mapToGraphql(
                commandService.login(userLoginInput.getUsername(), userLoginInput.getPassword()));
        var userInfo = accountInfo(token.getAuthToken());

        return UserResponse.newBuilder().authToken(token).user(userInfo).build();
    }

    @DgsMutation
    public UserActivationResponse userActivation(
            @InputArgument(name = "user") UserActivationInput userActivationInput) {
        return null;
    }
}
