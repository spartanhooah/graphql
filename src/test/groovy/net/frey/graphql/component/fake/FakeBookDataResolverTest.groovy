package net.frey.graphql.component.fake

import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest
import net.datafaker.Faker
import net.frey.graphql.generated.client.BooksGraphQLQuery
import net.frey.graphql.generated.client.BooksProjectionRoot
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class FakeBookDataResolverTest extends Specification {
    @Autowired
    DgsQueryExecutor queryExecutor

    @Autowired
    Faker faker

    def "test all books"() {
        given:
        def query = new BooksGraphQLQuery.Builder().build();
        def projectionRoot = new BooksProjectionRoot()
            .title()
            .author().name()
                .originCountry()
            .getRoot()
            .released()
            .year()

        def request = new GraphQLQueryRequest(query, projectionRoot).serialize()

        when:
        def titles = queryExecutor.executeAndExtractJsonPath(query, "data.books[*].title")

        then:
        titles.size() > 0
    }
}