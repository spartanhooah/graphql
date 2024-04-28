package net.frey.graphql.datasource.problemz.repository;

import java.util.UUID;
import net.frey.graphql.datasource.problemz.entity.Solutionz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionzRepository extends CrudRepository<Solutionz, UUID> {}
