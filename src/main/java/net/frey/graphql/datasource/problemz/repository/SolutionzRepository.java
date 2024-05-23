package net.frey.graphql.datasource.problemz.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import net.frey.graphql.datasource.problemz.entity.Solutionz;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionzRepository extends CrudRepository<Solutionz, UUID> {
    @Query(nativeQuery = true, value = "select * from solutionz where upper(content) like upper(:keyword)")
    List<Solutionz> findByKeyword(@Param("keyword") String keyword);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update problemz.solutionz set thumbs_ups = thumbs_ups + 1 where id = :solutionzId")
    void addThumbsUp(@Param("solutionzId") UUID id);

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update problemz.solutionz set thumbs_ups = thumbs_ups + 1 where id = :solutionzId")
    void addThumbsDown(@Param("solutionzId") UUID id);
}
