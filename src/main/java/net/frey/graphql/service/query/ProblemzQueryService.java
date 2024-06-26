package net.frey.graphql.service.query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    public Optional<Problemz> problemzById(UUID problemzId) {
        return repository.findById(problemzId);
    }

    public List<Problemz> problemzByKeyword(String keyword) {
        return repository.findByKeyword("%" + keyword + "%");
    }
}
