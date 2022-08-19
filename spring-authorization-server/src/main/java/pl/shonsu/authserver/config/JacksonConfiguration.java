package pl.shonsu.authserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.jackson2.CoreJackson2Module;

@Configuration
public class JacksonConfiguration {

    /**
     * Support for Java date and time API.
     *
     * @return the corresponding Jackson module.
     */
    @Bean
    public JavaTimeModule javaTimeModule() {
        return new JavaTimeModule();
    }

    @Bean
    public Jdk8Module jdk8TimeModule() {
        return new Jdk8Module();
    }

    /*
     * Support for Hibernate types in Jackson.
     */
    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

    /*
     * Module for serialization/deserialization of RFC7807 Problem.
     */
//    @Bean
//    public ProblemModule problemModule() {
//        return new ProblemModule();
//    }

    /*
     * Module for serialization/deserialization of ConstraintViolationProblem.
     */
//    @Bean
//    public ConstraintViolationProblemModule constraintViolationProblemModule() {
//        return new ConstraintViolationProblemModule();
//    }

    /**
     * To (de)serialize a BadCredentialsException, use CoreJackson2Module:
     */
    @Bean
    public CoreJackson2Module coreJackson2Module() {
        return new CoreJackson2Module();
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(coreJackson2Module());
        mapper.registerModule(javaTimeModule());
        mapper.registerModule(jdk8TimeModule());
        mapper.registerModule(hibernate5Module());
//        mapper.registerModule(problemModule());
//        mapper.registerModule(constraintViolationProblemModule());
        return mapper;
    }
}

