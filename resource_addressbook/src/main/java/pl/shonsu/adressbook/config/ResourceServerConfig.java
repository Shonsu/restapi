package pl.shonsu.adressbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http.antMatcher("/persons")
//                        .authorizeRequests()
//                                .antMatchers("/persons/**","/persons?page=*&sort=*")//?page=*&sort=*"
//                .access("hasAuthority('SCOPE_persons.read')")
//                //.hasRole('ROLE_USER')
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
        http.authorizeRequests(authz -> authz
                .antMatchers(HttpMethod.GET, "/persons/**","/persons").hasAuthority("SCOPE_persons.read")
                .antMatchers(HttpMethod.POST, "/persons").hasAuthority("SCOPE_persons.write")
                .anyRequest().denyAll())
                        .oauth2ResourceServer().jwt();


//
//        http.antMatcher("/persons/")
//                .authorizeRequests()
//                .antMatchers("/persons/**")
//                .access("hasAuthority('SCOPE_persons.read')")
//                //.hasRole("ADMIN")
////                .and()
////                .mvcMatcher("/test")
////                .authorizeRequests()
////                .mvcMatchers("/test")
////                .hasRole("USER")
//                //.access("hasAuthority('SCOPE_persons.read')")
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
        //http.csrf().disable();
        return http.build();
    }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return JwtDecoders.fromIssuerLocation("http://auth-server:9000");
//    }
}
