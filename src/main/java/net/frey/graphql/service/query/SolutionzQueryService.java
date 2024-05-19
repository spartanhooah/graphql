package net.frey.graphql.service.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.frey.graphql.datasource.problemz.entity.Solutionz;
import net.frey.graphql.datasource.problemz.repository.SolutionzRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SolutionzQueryService {
    private final SolutionzRepository repository;

    public List<Solutionz> solutionzByKeyword(String keyword) {
        return repository.findByKeyword("%" + keyword + "%");
    }
}
