package net.frey.graphql.datasource.fake;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import net.frey.graphql.generated.types.Hello;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FakeHelloDataSource {
    private final Faker faker;

    public static final List<Hello> HELLO_LIST = new ArrayList<>();

    @PostConstruct
    private void postConstruct() {
        for (int i = 0; i < 20; i++) {
            var hello = Hello.newBuilder()
                    .randomNumber(faker.random().nextInt(5000))
                    .text(faker.company().name())
                    .build();

            HELLO_LIST.add(hello);
        }
    }
}
