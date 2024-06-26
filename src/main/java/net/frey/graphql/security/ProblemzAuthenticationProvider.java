package net.frey.graphql.security;

import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import net.frey.graphql.datasource.problemz.entity.Userz;
import net.frey.graphql.datasource.problemz.repository.UserzRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@RequiredArgsConstructor
public class ProblemzAuthenticationProvider implements AuthenticationProvider {
    private final UserzRepository userzRepository;

    @Override
    public Authentication authenticate(Authentication auth) {
        var user = userzRepository.findByToken(auth.getCredentials().toString()).orElse(new Userz());

        return new UsernamePasswordAuthenticationToken(
                user, auth.getCredentials().toString(), getAuthorities(user.getUserRole()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String userRole) {
        var authorities = new ArrayList<GrantedAuthority>();

        if (StringUtils.isNotBlank(userRole)) {
            authorities.add(new SimpleGrantedAuthority(userRole));
        }

        return authorities;
    }
}
