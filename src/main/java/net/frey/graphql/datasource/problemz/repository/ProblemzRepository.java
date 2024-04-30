package net.frey.graphql.datasource.problemz.repository;

import java.util.List;
import java.util.UUID;
import net.frey.graphql.datasource.problemz.entity.Problemz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemzRepository extends CrudRepository<Problemz, UUID> {
    List<Problemz> findAllByOrderByCreationTimestampDesc();
}
