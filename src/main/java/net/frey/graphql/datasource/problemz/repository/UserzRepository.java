package net.frey.graphql.datasource.problemz.repository;

import java.util.Optional;
import java.util.UUID;
import net.frey.graphql.datasource.problemz.entity.Userz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserzRepository extends CrudRepository<Userz, UUID> {
    Optional<Userz> findByUsernameIgnoreCase(String username);

    @Query(
            nativeQuery = true,
            value =
                    """
        select u.*
        from userz u inner join userz_token ut on u.id = ut.user_id
        where ut.auth_token = ?
        and ut.expiry_timestamp > current_timestamp""")
    Optional<Userz> findByToken(String authToken);
}
