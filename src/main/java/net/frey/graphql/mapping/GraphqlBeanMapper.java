package net.frey.graphql.mapping;

import static net.frey.graphql.generated.types.SolutionType.EXAMPLE;
import static net.frey.graphql.generated.types.SolutionType.REFERENCE;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import net.frey.graphql.datasource.problemz.entity.Problemz;
import net.frey.graphql.datasource.problemz.entity.Solutionz;
import net.frey.graphql.datasource.problemz.entity.Userz;
import net.frey.graphql.datasource.problemz.entity.UserzToken;
import net.frey.graphql.generated.types.Problem;
import net.frey.graphql.generated.types.ProblemCreateInput;
import net.frey.graphql.generated.types.Solution;
import net.frey.graphql.generated.types.User;
import net.frey.graphql.generated.types.UserAuthToken;
import org.apache.commons.lang3.StringUtils;
import org.ocpsoft.prettytime.PrettyTime;

public class GraphqlBeanMapper {
    private static final PrettyTime PRETTY_TIME = new PrettyTime();
    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(-5);

    public static User mapToGraphql(Userz original) {
        var result = new User();
        var createDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);

        result.setAvatar(original.getAvatar());
        result.setCreateDateTime(createDateTime);
        result.setDisplayName(original.getDisplayName());
        result.setEmail(original.getEmail());
        result.setId(original.getId().toString());
        result.setUsername(original.getUsername());

        return result;
    }

    public static Problem mapToGraphql(Problemz original) {
        var result = new Problem();
        var createDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);
        var author = mapToGraphql(original.getCreatedBy());
        var solutions = original.getSolutions().stream()
                .map(GraphqlBeanMapper::mapToGraphql)
                .sorted(Comparator.comparing(Solution::getCreateDateTime).reversed())
                .toList();
        var tags = List.of(original.getTags().split(","));

        result.setAuthor(author);
        result.setId(original.getId().toString());
        result.setContent(original.getContent());
        result.setCreateDateTime(createDateTime);
        result.setPrettyCreateDateTime(PRETTY_TIME.format(createDateTime));
        result.setSolutionCount(solutions.size());
        result.setSolutions(solutions);
        result.setTags(tags);
        result.setTitle(original.getTitle());

        return result;
    }

    public static Solution mapToGraphql(Solutionz original) {
        var result = new Solution();
        var createDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);
        var author = mapToGraphql(original.getCreatedBy());
        var type = StringUtils.equalsIgnoreCase(original.getType(), EXAMPLE.name()) ? EXAMPLE : REFERENCE;

        result.setAuthor(author);
        result.setType(type);
        result.setContent(original.getContent());
        result.setCreateDateTime(createDateTime);
        result.setId(original.getId().toString());
        result.setThumbsUps(original.getThumbsUps());
        result.setThumbsDowns(original.getThumbsDowns());
        result.setPrettyCreateDateTime(PRETTY_TIME.format(createDateTime));

        return result;
    }

    public static UserAuthToken mapToGraphql(UserzToken original) {
        var result = new UserAuthToken();

        result.setAuthToken(original.getAuthToken());
        result.setExpiryTime(original.getExpiryTimestamp().atOffset(ZONE_OFFSET));

        return result;
    }

    public static Problemz mapToEntity(ProblemCreateInput input, Userz author) {
        var result = new Problemz();

        result.setContent(input.getContent());
        result.setCreatedBy(author);
        result.setId(UUID.randomUUID());
        result.setSolutions(Collections.emptyList());
        result.setTags(String.join(",", input.getTags()));
        result.setTitle(input.getTitle());
        result.setCreationTimestamp(LocalDateTime.now());

        return result;
    }
}
