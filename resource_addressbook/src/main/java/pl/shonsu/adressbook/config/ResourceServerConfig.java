package pl.shonsu.adressbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.mvcMatcher("/persons/**")
                .authorizeRequests()
                .mvcMatchers("/persons/**")
                .access("hasAuthority('SCOPE_persons.read')")
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}
