package pl.shonsu.clientserver.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import pl.shonsu.clientserver.dtos.PersonDto;
import pl.shonsu.clientserver.response.PersonDtoPageableResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;


@RestController
public class PersonsController {
    @Autowired
    private WebClient webClient;


    @GetMapping(value = "/persons")
    public PersonDtoPageableResponse getPersons(
            @RegisteredOAuth2AuthorizedClient("persons-client-authorization-code") OAuth2AuthorizedClient authorizedClient,
            @RequestParam(required = false) Integer page,
            String sort
    ) {

        int pageNumber = page != null && page > 0 ? page : 0;
        String sortDirection = sort != null ? sort : "ASC";
        String str = Objects.requireNonNull(this.webClient.get().uri("http://127.0.0.1:8090/persons", uriBuilder -> uriBuilder
                .queryParam("page", pageNumber)
                .queryParam("sort", sortDirection)
                .build()).retrieve().toEntity(String.class).block()).getBody();
        System.out.println(str);

        return this.webClient.get()
                .uri("http://127.0.0.1:8090/persons", uriBuilder -> uriBuilder
                        .queryParam("page", pageNumber)
                        .queryParam("sort", sortDirection)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono( PersonDtoPageableResponse.class).block();
    }

    @GetMapping("/persons/{id}")
    public PersonDto getPersonById(@RegisteredOAuth2AuthorizedClient("persons-client-authorization-code") OAuth2AuthorizedClient authorizedClient,
                                   @PathVariable long id) {

        return this.webClient
                .get()

                .uri("http://127.0.0.1:8090", uriBuilder -> uriBuilder
                        .path("/persons/{id}")
                        .build(id))
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(PersonDto.class).block();
    }

    @GetMapping("/test")
    public String[] getTest(@RegisteredOAuth2AuthorizedClient("persons-client-authorization-code") OAuth2AuthorizedClient authorizedClient) {

        return this.webClient
                .get()
                .uri("http://127.0.0.1:8090/test")
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String[].class).block();
    }
}
