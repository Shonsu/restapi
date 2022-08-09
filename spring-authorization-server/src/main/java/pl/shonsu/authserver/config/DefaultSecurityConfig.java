package pl.shonsu.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.shonsu.authserver.user.service.UserService;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class DefaultSecurityConfig {

    @Autowired
    DataSource dataSource;
    @Autowired
    UserService userService;

    // @formatter:off
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().authenticated()
                )
                .formLogin(withDefaults());
        return http.build();
    }

    // @formatter:on

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
//        auth
//                .inMemoryAuthentication()
//                .withUser("Shonsu")
//                .password(passwordEncoder().encode("Test123$")).roles("ADMIN");
       // System.out.println(passwordEncoder().encode("Test123$"));
    }
//    @Bean
//    UserDetailsService users() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user1")
//                .password("password")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
    // @formatter:on


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}