package net.frey.graphql.component.fake;

import static net.frey.graphql.datasource.fake.FakePetDataSource.PET_LIST;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import net.frey.graphql.generated.DgsConstants;
import net.frey.graphql.generated.DgsConstants.QUERY;
import net.frey.graphql.generated.types.Cat;
import net.frey.graphql.generated.types.Dog;
import net.frey.graphql.generated.types.Pet;
import net.frey.graphql.generated.types.PetFilter;

@DgsComponent
public class FakePetDataResolver {
    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = QUERY.Pets)
    public List<Pet> getPets(@InputArgument(name = QUERY.PETS_INPUT_ARGUMENT.PetFilter) PetFilter filter) {
        if (filter == null) {
            return PET_LIST;
        }

        return PET_LIST.stream().filter(pet -> matchesFilter(filter, pet)).toList();
    }

    private boolean matchesFilter(PetFilter filter, Pet pet) {
        if (StringUtils.isBlank(filter.getPetType())) {
            return true;
        }

        if (filter.getPetType().equalsIgnoreCase(Dog.class.getSimpleName())) {
            return pet instanceof Dog;
        } else if (filter.getPetType().equalsIgnoreCase(Cat.class.getSimpleName())) {
            return pet instanceof Cat;
        } else {
            return false;
        }
    }
}
