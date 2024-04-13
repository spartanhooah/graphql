package net.frey.graphql.component.fake;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import java.util.Map;
import net.frey.graphql.datasource.fake.FakeBookDataSource;
import net.frey.graphql.generated.DgsConstants;
import net.frey.graphql.generated.DgsConstants.QUERY;
import net.frey.graphql.generated.DgsConstants.RELEASEHISTORYINPUT;
import net.frey.graphql.generated.types.Book;
import net.frey.graphql.generated.types.ReleaseHistory;
import net.frey.graphql.generated.types.ReleaseHistoryInput;
import org.apache.commons.lang3.StringUtils;

@DgsComponent
public class FakeBookDataResolver {
    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = QUERY.Books)
    public List<Book> booksWrittenBy(@InputArgument(name = "author") String authorName) {
        if (StringUtils.isBlank(authorName)) {
            return FakeBookDataSource.BOOK_LIST;
        } else {
            return FakeBookDataSource.BOOK_LIST.stream()
                    .filter(book ->
                            StringUtils.containsIgnoreCase(book.getAuthor().getName(), authorName))
                    .toList();
        }
    }

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = QUERY.BooksByReleased)
    public List<Book> getBooksByRelease(DataFetchingEnvironment dataFetchingEnvironment) {
        var releasedMap = (Map<String, Object>) dataFetchingEnvironment.getArgument("releaseHistory");
        var releasedInput = ReleaseHistoryInput.newBuilder()
                .printedEdition((boolean) releasedMap.get(RELEASEHISTORYINPUT.PrintedEdition))
                .year((int) releasedMap.get(RELEASEHISTORYINPUT.Year))
                .build();

        return FakeBookDataSource.BOOK_LIST.stream()
                .filter(book -> matchReleaseHistory(releasedInput, book.getReleased()))
                .toList();
    }

    private boolean matchReleaseHistory(ReleaseHistoryInput input, ReleaseHistory history) {
        return input.getPrintedEdition() == history.getPrintedEdition() && input.getYear() == history.getYear();
    }
}
