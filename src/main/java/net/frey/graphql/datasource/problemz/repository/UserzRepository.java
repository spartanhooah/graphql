package net.frey.graphql.datasource.problemz.repository;

import java.util.UUID;
import net.frey.graphql.datasource.problemz.entity.Userz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserzRepository extends CrudRepository<Userz, UUID> {}
