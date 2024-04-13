package net.frey.graphql.datasource.fake;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import net.datafaker.Faker;
import net.frey.graphql.generated.types.Address;
import net.frey.graphql.generated.types.Author;
import net.frey.graphql.generated.types.Book;
import net.frey.graphql.generated.types.ReleaseHistory;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakeBookDataSource {
    public static final List<Book> BOOK_LIST = new ArrayList<>();

    @Autowired
    public FakeBookDataSource(Faker faker) {
        BOOK_LIST.addAll(getBookStream(faker));
    }

    private static @NotNull List<Book> getBookStream(Faker faker) {
        return IntStream.range(0, 20)
                .mapToObj(number -> {
                    var addresses = IntStream.range(
                                    0, ThreadLocalRandom.current().nextInt(1, 3))
                            .mapToObj(addressCount -> Address.newBuilder()
                                    .country(faker.address().country())
                                    .city(faker.address().cityName())
                                    .country(faker.address().country())
                                    .street(faker.address().streetAddress())
                                    .zipCode(faker.address().zipCode())
                                    .build())
                            .toList();
                    var author = Author.newBuilder()
                            .addresses(addresses)
                            .name(faker.book().author())
                            .originCountry(faker.country().name())
                            .build();
                    var released = ReleaseHistory.newBuilder()
                            .printedEdition(faker.bool().bool())
                            .releasedCountry(faker.country().name())
                            .year(faker.number().numberBetween(2019, 2021))
                            .build();

                    return Book.newBuilder()
                            .author(author)
                            .publisher(faker.book().publisher())
                            .title(faker.book().title())
                            .released(released)
                            .build();
                })
                .toList();
    }
}
