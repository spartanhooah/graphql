package net.frey.graphql.service.command;

import lombok.RequiredArgsConstructor;
import net.frey.graphql.datasource.problemz.entity.Problemz;
import net.frey.graphql.datasource.problemz.repository.ProblemzRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
public class ProblemzCommandService {
    private Sinks.Many<Problemz> problemzSink = Sinks.many().multicast().onBackpressureBuffer();

    private final ProblemzRepository repository;

    public Problemz createProblem(Problemz problem) {
        Problemz saved = repository.save(problem);

        problemzSink.tryEmitNext(saved);

        return saved;
    }

    public Flux<Problemz> problemzSubscription() {
        return problemzSink.asFlux();
    }
}
