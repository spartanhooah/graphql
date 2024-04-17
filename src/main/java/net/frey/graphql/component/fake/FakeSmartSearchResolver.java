package net.frey.graphql.component.fake;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import net.frey.graphql.generated.DgsConstants;
import net.frey.graphql.generated.DgsConstants.QUERY;
import net.frey.graphql.generated.types.SmartSearchResult;

import java.util.ArrayList;
import java.util.List;

import static net.frey.graphql.datasource.fake.FakeBookDataSource.BOOK_LIST;
import static net.frey.graphql.datasource.fake.FakeHelloDataSource.HELLO_LIST;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

@DgsComponent
public class FakeSmartSearchResolver {
    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = QUERY.SmartSearch)
    public List<SmartSearchResult> getSmartSearch(@InputArgument(name = QUERY.SMARTSEARCH_INPUT_ARGUMENT.Keyword) String keyword) {
        var smartSearchList = new ArrayList<SmartSearchResult>();

        if (keyword == null) {
            smartSearchList.addAll(BOOK_LIST);
            smartSearchList.addAll(HELLO_LIST);
        } else {
            HELLO_LIST.stream().filter(hello ->
                    containsIgnoreCase(hello.getText(), keyword))
                    .forEach(smartSearchList::add);

            BOOK_LIST.stream().filter(book ->
                    containsIgnoreCase(book.getTitle(), keyword))
                    .forEach(smartSearchList::add);
        }

        return smartSearchList;
    }
}
