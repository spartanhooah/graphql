package net.frey.graphql.security;

import lombok.RequiredArgsConstructor;
import net.frey.graphql.datasource.problemz.repository.UserzRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class ProblemzSecurityConfig {
    private final UserzRepository userzRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //        http.apply(ProblemzHttpConfigurer.newInstance());
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .authenticationProvider(new ProblemzAuthenticationProvider(userzRepository))
                .build();
    }
}
