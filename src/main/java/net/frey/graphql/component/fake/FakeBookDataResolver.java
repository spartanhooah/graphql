package net.frey.graphql.component.fake;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import java.util.List;
import net.frey.graphql.datasource.fake.FakeBookDataSource;
import net.frey.graphql.generated.types.Book;
import org.apache.commons.lang3.StringUtils;

@DgsComponent
public class FakeBookDataResolver {
    @DgsData(parentType = "Query", field = "books")
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
}
