package net.frey.graphql.client

import net.frey.graphql.client.request.GraphqlRestRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class StarWarsRestClientTest extends Specification {
    @Autowired
    StarWarsRestClient client

    def "test as json"() {
        given:
        def query = """
            query allPlanets {
              allPlanets {
                planets {
                  name
                  climates
                  terrains
                }
              }
            }"""
        def requestBody = new GraphqlRestRequest(query, null)

        when:
        def response = client.asJson(requestBody, null)

        then:
        response
    }

    def "invalid request"() {
        given:
        def query = """
            query allPlanets {
              allPlanetsxxxxx {
                planets {
                  name
                  climates
                  terrains
                }
              }
            }"""
        def requestBody = new GraphqlRestRequest(query, null)

        when:
        client.asJson(requestBody, null)

        then:
        thrown(Exception)
    }

    def "test all planets"() {
        when:
        def result = client.getAllPlanets()

        then:
        !result.isEmpty()
    }

    def "test one ship"() {
        when:
        def result = client.oneStarshipFixed()

        then:
        result.model()
        result.name()
        result.manufacturers()
    }

    def "test one film - happy path"() {
        when:
        def result = client.oneFilm("1")

        then:
        result.title() == "A New Hope"
    }

    def "test one film - sad path"() {
        expect:
        !client.oneFilmInvalid().isEmpty()
    }
}
