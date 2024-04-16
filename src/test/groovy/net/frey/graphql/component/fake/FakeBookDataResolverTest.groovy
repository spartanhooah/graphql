package net.frey.graphql.component.fake

import com.jayway.jsonpath.TypeRef
import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest
import net.datafaker.Faker
import net.frey.graphql.generated.client.BooksByReleasedGraphQLQuery
import net.frey.graphql.generated.client.BooksGraphQLQuery
import net.frey.graphql.generated.client.BooksProjectionRoot
import net.frey.graphql.generated.types.Author
import net.frey.graphql.generated.types.ReleaseHistoryInput
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
        def titles = queryExecutor.executeAndExtractJsonPath(request, "data.books[*].title")

        then:
        titles.size() > 0

        and:
        when:
        def authors = queryExecutor.executeAndExtractJsonPathAsObject(
            request,
            "data.books[*].author",
            new TypeRef<List<Author>>(){})

        then:
        titles.size() == authors.size()

        and:
        when:
        def releaseYears = queryExecutor.executeAndExtractJsonPathAsObject(
            request,
            "data.books[*].released.year",
            new TypeRef<List<Integer>>() {})

        then:
        releaseYears.size() == titles.size()
    }

    def "Use the query input"() {
        given:
        def expectedYear = faker.number().numberBetween(2019, 2021)
        def expectedPrintedEdition = faker.bool().bool()
        def queryInput = new ReleaseHistoryInput(expectedYear, expectedPrintedEdition)
        def query = BooksByReleasedGraphQLQuery.newRequest()
            .releaseHistory(queryInput)
            .build()
        def projectionRoot = new BooksProjectionRoot().released().year().printedEdition()
        def request = new GraphQLQueryRequest(query, projectionRoot).serialize()

        when: 'the release years are collected'
        def releaseYears = queryExecutor.executeAndExtractJsonPath(request, "data.booksByReleased[*].released.year")

        then: 'all the years are the same'
        releaseYears.every { it == expectedYear }

        and:
        when: 'the printed edition indicators are collected'
        def releasePrintedEditions = queryExecutor.executeAndExtractJsonPath(request, "data.booksByReleased[*].released.printedEdition")

        then: 'all the indicators are the same'
        releasePrintedEditions.every { it == expectedPrintedEdition }
    }
}
