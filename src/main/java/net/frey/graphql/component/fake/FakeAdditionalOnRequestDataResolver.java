package net.frey.graphql.component.fake;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import net.frey.graphql.generated.DgsConstants;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@DgsComponent
public class FakeAdditionalOnRequestDataResolver {
    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.AdditionalOnRequest)
    public String additionalOnRequest(
            @RequestHeader(name = "optionalHeader") String optionalHeader,
            @RequestHeader(name = "mandatoryHeader") String mandatoryHeader,
            @RequestParam(name = "optionalParam") String optionalParam,
            @RequestParam(name = "mandatoryParam") String mandatoryParam) {
        return "Optional header: " + optionalHeader + ","
                + "Mandatory header: " + mandatoryHeader + ","
                + "Optional param: " + optionalParam + ","
                + "Mandatory param: " + mandatoryParam;
    }
}
