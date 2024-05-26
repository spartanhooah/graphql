package net.frey.graphql.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class ProblemzSecurityFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
            throws ServletException, IOException {
        var authToken = request.getHeader("authToken");

        if (StringUtils.isBlank(authToken)) {
            authToken = StringUtils.EMPTY;
        }

        var authentication = new UsernamePasswordAuthenticationToken(null, authToken);

        var authenticated = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authenticated);

        filterChain.doFilter(request, response);
    }
}
