package net.frey.graphql.security;

// public class ProblemzHttpConfigurer extends AbstractHttpConfigurer<ProblemzHttpConfigurer, HttpSecurity> {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        var authenticationManager = http.getSharedObject(AuthenticationManager.class);
//        http.addFilterBefore(
//                new ProblemzSecurityFilter(authenticationManager),
//                UsernamePasswordAuthenticationFilter.class
//        );
//    }
//
//    public static ProblemzHttpConfigurer newInstance() {
//        return new ProblemzHttpConfigurer();
//    }
//
// }
