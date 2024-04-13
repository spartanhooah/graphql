package net.frey.graphql.component.fake

import com.netflix.graphql.dgs.DgsQueryExecutor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class FakeHelloDataResolverTest extends Specification {
    @Autowired
    DgsQueryExecutor queryExecutor

    def "test one hello"() {
        given:
        def query = """
        {
          oneHello {
            text
            randomNumber
          }
        }"""

        when:
        def text = queryExecutor.executeAndExtractJsonPath(query, "data.oneHello.text")
        def randomNumber = queryExecutor.executeAndExtractJsonPath(query, "data.oneHello.randomNumber")

        then:
        text
        randomNumber
    }

    def "test all hellos"() {
        given:
        def query = """
        {
          allHellos {
            text
            randomNumber
          }
        }"""

        when:
        def text = queryExecutor.executeAndExtractJsonPath(query, "data.allHellos[*].text")
        def randomNumber = queryExecutor.executeAndExtractJsonPath(query, "data.allHellos[*].randomNumber")

        then:
        text.size() > 0
        randomNumber.size() > 0
    }
}
