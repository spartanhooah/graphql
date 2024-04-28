package net.frey.graphql.component.fake;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsSubscription;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import net.frey.graphql.datasource.fake.FakeStockDataSource;
import net.frey.graphql.generated.types.Stock;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

@DgsComponent
@RequiredArgsConstructor
public class FakeStockDataResolver {
    private final FakeStockDataSource dataSource;

    @DgsSubscription
    public Publisher<Stock> randomStock() {
        return Flux.interval(Duration.ofSeconds(3)).map(t -> dataSource.randomStock());
    }
}
