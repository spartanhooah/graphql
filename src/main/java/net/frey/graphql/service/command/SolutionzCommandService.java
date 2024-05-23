package net.frey.graphql.service.command;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.frey.graphql.datasource.problemz.entity.Solutionz;
import net.frey.graphql.datasource.problemz.repository.SolutionzRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SolutionzCommandService {
    private final SolutionzRepository repository;

    public Solutionz createSolutionz(Solutionz solutionz) {
        return repository.save(solutionz);
    }

    public Optional<Solutionz> thumbsDown(UUID solutionId) {
        repository.addThumbsDown(solutionId);

        return repository.findById(solutionId);
    }

    public Optional<Solutionz> thumbsUp(UUID solutionId) {
        repository.addThumbsUp(solutionId);

        return repository.findById(solutionId);
    }
}
