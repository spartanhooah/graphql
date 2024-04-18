package net.frey.graphql.datasource.fake;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import net.frey.graphql.generated.types.Stock;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;

@Configuration
@RequiredArgsConstructor
public class FakeStockDataSource {
    private final Faker faker;

    public Stock randomStock() {
        return Stock.newBuilder().lastTradeDateTime(OffsetDateTime.now())
                .price(faker.random().nextInt(100, 1000))
                .symbol(faker.stock().nyseSymbol())
                .build();
    }
}
