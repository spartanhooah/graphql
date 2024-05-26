package net.frey.graphql.component.problemz;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.frey.graphql.exception.ProblemzAuthenticationException;
import net.frey.graphql.exception.ProblemzPermissionException;
import net.frey.graphql.generated.types.*;
import net.frey.graphql.mapping.GraphqlBeanMapper;
import net.frey.graphql.service.command.UserzCommandService;
import net.frey.graphql.service.query.UserzQueryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;
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

    @Secured("ROLE_ADMIN")
    @DgsMutation(field = "userCreate")
    public UserResponse createUser(@InputArgument(name = "user") UserCreateInput userCreateInput) {
        // No longer needed after adding security classes
        //        validateAdmin(authToken);

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
    @Secured("ROLE_ADMIN")
    public UserActivationResponse userActivation(
            @InputArgument(name = "user") UserActivationInput userActivationInput) {
        // No longer needed after adding security classes
        //        validateAdmin(authToken);

        return commandService
                .activateUser(userActivationInput.getUsername(), userActivationInput.getActive())
                .map(updated -> UserActivationResponse.newBuilder()
                        .isActive(updated.isActive())
                        .build())
                .orElseThrow(DgsEntityNotFoundException::new);
    }

    private void validateAdmin(String authToken) {
        var userAuth = queryService
                .findUserzByAuthToken(authToken)
                .orElseThrow(() -> new ProblemzAuthenticationException("User auth token not found"));

        if (StringUtils.equals(userAuth.getUserRole(), "ROLE_ADMIN")) {
            throw new ProblemzPermissionException("User does not have admin role.");
        }
    }
}
