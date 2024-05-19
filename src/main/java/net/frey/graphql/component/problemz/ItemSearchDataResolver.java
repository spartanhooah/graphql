package net.frey.graphql.component.problemz;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.frey.graphql.generated.DgsConstants;
import net.frey.graphql.generated.types.SearchItemFilter;
import net.frey.graphql.generated.types.SearchableItem;
import net.frey.graphql.mapping.GraphqlBeanMapper;
import net.frey.graphql.service.query.ProblemzQueryService;
import net.frey.graphql.service.query.SolutionzQueryService;

@DgsComponent
@RequiredArgsConstructor
public class ItemSearchDataResolver {
    private final ProblemzQueryService problemzService;
    private final SolutionzQueryService solutionzService;

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ItemSearch)
    public List<SearchableItem> searchItems(@InputArgument(name = "filter") SearchItemFilter filter) {
        var keyword = filter.getKeyword();

        var result = new ArrayList<SearchableItem>(problemzService.problemzByKeyword(keyword).stream()
                .map(GraphqlBeanMapper::mapToGraphql)
                .toList());

        result.addAll(solutionzService.solutionzByKeyword(keyword).stream()
                .map(GraphqlBeanMapper::mapToGraphql)
                .toList());

        result.sort(Comparator.comparing(SearchableItem::getCreateDateTime).reversed());

        return result;
    }
}
