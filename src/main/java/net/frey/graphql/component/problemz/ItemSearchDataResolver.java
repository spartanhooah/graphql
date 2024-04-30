package net.frey.graphql.component.problemz;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import java.util.List;
import net.frey.graphql.generated.DgsConstants;
import net.frey.graphql.generated.types.SearchItemFilter;
import net.frey.graphql.generated.types.SearchableItem;

@DgsComponent
public class ItemSearchDataResolver {
    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ItemSearch)
    public List<SearchableItem> searchItems(@InputArgument(name = "filter") SearchItemFilter filter) {
        return null;
    }
}
