package net.frey.graphql.component.fake;

import static net.frey.graphql.datasource.fake.FakeMobileAppDataSource.MOBILE_APP_LIST;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import java.util.List;
import net.frey.graphql.generated.DgsConstants;
import net.frey.graphql.generated.DgsConstants.QUERY;
import net.frey.graphql.generated.DgsConstants.QUERY.MOBILEAPPS_INPUT_ARGUMENT;
import net.frey.graphql.generated.types.MobileApp;
import net.frey.graphql.generated.types.MobileAppFilter;
import org.apache.commons.lang3.StringUtils;

@DgsComponent
public class FakeMobileAppDataResolver {
    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = QUERY.MobileApps)
    public List<MobileApp> getMobileApps(
            @InputArgument(name = MOBILEAPPS_INPUT_ARGUMENT.MobileAppFilter) MobileAppFilter filter) {
        if (filter == null) {
            return MOBILE_APP_LIST;
        }

        return MOBILE_APP_LIST.stream().filter(app -> matchFilter(filter, app)).toList();
    }

    private boolean matchFilter(MobileAppFilter filter, MobileApp app) {
        var doesAppMatch = containsIgnoreCase(app.getName(), defaultIfBlank(filter.getName(), StringUtils.EMPTY))
                && containsIgnoreCase(app.getVersion(), defaultIfBlank(filter.getVersion(), StringUtils.EMPTY));

        if (!doesAppMatch) {
            return false;
        }

        if (StringUtils.isNotBlank(filter.getPlatform())
                && !app.getPlatform().contains(filter.getPlatform().toLowerCase())) {
            return false;
        }

        return filter.getAuthor() == null
                || containsIgnoreCase(
                        app.getAuthor().getName(),
                        defaultIfBlank(filter.getAuthor().getName(), StringUtils.EMPTY));
    }
}
