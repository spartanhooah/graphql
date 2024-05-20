package net.frey.graphql.service.command;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.frey.graphql.datasource.problemz.entity.UserzToken;
import net.frey.graphql.datasource.problemz.repository.UserzRepository;
import net.frey.graphql.datasource.problemz.repository.UserzTokenRepository;
import net.frey.graphql.exception.ProblemzAuthenticationException;
import net.frey.graphql.utility.HashUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserzCommandService {
    private final UserzRepository userzRepository;
    private final UserzTokenRepository tokenRepository;

    public UserzToken login(String username, String password) {
        return userzRepository
                .findByUsernameIgnoreCase(username)
                .map(user -> {
                    if (!HashUtil.doesBcryptMatch(password, user.getHashedPassword())) {
                        throw new ProblemzAuthenticationException("Invalid credential.");
                    }

                    return refreshToken(user.getId(), RandomStringUtils.randomAlphanumeric(40));
                })
                .orElseThrow(() -> new ProblemzAuthenticationException("Invalid credential."));
    }

    private UserzToken refreshToken(UUID userId, String authToken) {
        var token = new UserzToken();
        token.setUserId(userId);
        token.setAuthToken(authToken);

        var now = LocalDateTime.now();
        token.setCreationTimestamp(now);
        token.setExpiryTimestamp(now.plusHours(2));

        return tokenRepository.save(token);
    }
}
