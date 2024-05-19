package net.frey.graphql.datasource.problemz.repository;

import java.util.List;
import java.util.UUID;
import net.frey.graphql.datasource.problemz.entity.Problemz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemzRepository extends CrudRepository<Problemz, UUID> {
    List<Problemz> findAllByOrderByCreationTimestampDesc();

    @Query(
            nativeQuery = true,
            value =
                    """
        select * from problemz p
        where upper(content) like upper(:keyword)
        or upper(title) like upper(:keyword)
        or upper(tags) like upper(:keyword)""")
    List<Problemz> findByKeyword(@Param("keyword") String keyword);
}
