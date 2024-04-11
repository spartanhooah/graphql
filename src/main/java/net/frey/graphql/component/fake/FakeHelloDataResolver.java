package net.frey.graphql.component.fake;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.frey.graphql.datasource.fake.FakeHelloDataSource;
import net.frey.graphql.generated.types.Hello;

@DgsComponent
public class FakeHelloDataResolver {
    @DgsQuery
    public List<Hello> allHellos() {
        return FakeHelloDataSource.HELLO_LIST;
    }

    @DgsQuery
    public Hello oneHello() {
        List<Hello> helloList = FakeHelloDataSource.HELLO_LIST;

        return helloList.get(ThreadLocalRandom.current().nextInt(helloList.size()));
    }
}
