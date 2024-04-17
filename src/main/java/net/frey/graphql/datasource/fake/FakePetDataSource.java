package net.frey.graphql.datasource.fake;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import net.datafaker.Faker;
import net.frey.graphql.generated.types.Cat;
import net.frey.graphql.generated.types.Dog;
import net.frey.graphql.generated.types.Pet;
import net.frey.graphql.generated.types.PetDietType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakePetDataSource {
    public static final List<Pet> PET_LIST = new ArrayList<>();

    @Autowired
    public FakePetDataSource(Faker faker) {
        PET_LIST.addAll(getPets(faker));
    }

    private List<Pet> getPets(Faker faker) {
        return IntStream.range(0, 10)
                .mapToObj(number -> {
                    if (number % 2 == 0) {
                        return Dog.newBuilder()
                                .name(faker.dog().name())
                                .diet(PetDietType.OMNIVORE)
                                .breed(faker.dog().breed())
                                .size(faker.dog().size())
                                .coatLength(faker.dog().coatLength())
                                .build();
                    }

                    return Cat.newBuilder()
                            .name(faker.cat().name())
                            .diet(PetDietType.CARNIVORE)
                            .breed(faker.cat().breed())
                            .registry(faker.cat().registry())
                            .build();
                })
                .toList();
    }
}
