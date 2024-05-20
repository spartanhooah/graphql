package net.frey.graphql.service.command;

import lombok.RequiredArgsConstructor;
import net.frey.graphql.datasource.problemz.entity.Problemz;
import net.frey.graphql.datasource.problemz.repository.ProblemzRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemzCommandService {
    private final ProblemzRepository repository;

    public Problemz createProblem(Problemz problem) {
        return repository.save(problem);
    }
}
