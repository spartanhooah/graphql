package net.frey.graphql.component.fake;

import static net.frey.graphql.datasource.fake.FakeHelloDataSource.HELLO_LIST;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import java.util.List;
import net.frey.graphql.generated.DgsConstants.MUTATION;
import net.frey.graphql.generated.types.Hello;
import net.frey.graphql.generated.types.HelloInput;

@DgsComponent
public class FakeHelloMutation {
    //    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.AddHello)
    // Below is equivalent when the method name and input argument are the same as the graphql definition
    @DgsMutation
    public int addHello(@InputArgument(name = "helloInput") HelloInput helloInput) {
        var hello = new Hello(helloInput.getText(), helloInput.getRandomNumber());
        HELLO_LIST.add(hello);

        return HELLO_LIST.size();
    }

    @DgsData(parentType = MUTATION.TYPE_NAME, field = MUTATION.ReplaceHelloText)
    public List<Hello> replaceHelloText(@InputArgument(name = "helloInput") HelloInput helloInput) {
        HELLO_LIST.stream()
                .filter(hello -> hello.getRandomNumber() == helloInput.getRandomNumber())
                .forEach(hello -> hello.setText(helloInput.getText()));

        return HELLO_LIST;
    }

    @DgsData(parentType = MUTATION.TYPE_NAME, field = MUTATION.DeleteHello)
    public int deleteHello(int number) {
        HELLO_LIST.removeIf(hello -> hello.getRandomNumber() == number);

        return HELLO_LIST.size();
    }
}
