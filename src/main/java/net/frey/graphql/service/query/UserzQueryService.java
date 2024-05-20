package net.frey.graphql.service.query;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.frey.graphql.datasource.problemz.entity.Userz;
import net.frey.graphql.datasource.problemz.repository.UserzRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserzQueryService {
    private final UserzRepository repository;

    public Optional<Userz> findUserzByAuthToken(String authToken) {
        return repository.findByToken(authToken);
    }
}
