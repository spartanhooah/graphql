package net.frey.graphql.service.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.frey.graphql.datasource.problemz.entity.Problemz;
import net.frey.graphql.datasource.problemz.repository.ProblemzRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemzQueryService {
    private final ProblemzRepository repository;

    public List<Problemz> latestProblemz() {
        return repository.findAllByOrderByCreationTimestampDesc();
    }
}
