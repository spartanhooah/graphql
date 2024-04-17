package net.frey.graphql.datasource.fake;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import net.datafaker.Faker;
import net.frey.graphql.generated.types.Address;
import net.frey.graphql.generated.types.Author;
import net.frey.graphql.generated.types.MobileApp;
import net.frey.graphql.generated.types.MobileAppCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakeMobileAppDataSource {
    public static final List<MobileApp> MOBILE_APP_LIST = new ArrayList<>();

    @Autowired
    public FakeMobileAppDataSource(Faker faker) {
        MOBILE_APP_LIST.addAll(getMobileApps(faker));
    }

    private List<MobileApp> getMobileApps(Faker faker) {
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
                            .name(faker.app().author())
                            .originCountry(faker.country().name())
                            .build();

                    URL homepage;
                    try {
                        homepage = new URL("https://" + faker.internet().url());
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }

                    return MobileApp.newBuilder()
                            .name(faker.app().name())
                            .author(author)
                            .version(faker.app().version())
                            .platform(randomMobileAppPlatform())
                            .appId(UUID.randomUUID().toString())
                            .releaseDate(
                                    LocalDate.now().minusDays(faker.random().nextInt(365)))
                            .downloads(faker.number().numberBetween(1, 1_500_000))
                            .homepage(homepage)
                            .category(
                                    MobileAppCategory.values()[
                                            faker.random().nextInt(MobileAppCategory.values().length)])
                            .build();
                })
                .toList();
    }

    private List<String> randomMobileAppPlatform() {
        return switch (ThreadLocalRandom.current().nextInt(10) % 3) {
            case 0 -> List.of("android");
            case 1 -> List.of("ios");
            default -> List.of("ios", "android");
        };
    }
}
