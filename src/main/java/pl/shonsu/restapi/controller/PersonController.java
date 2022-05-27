package pl.shonsu.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.shonsu.restapi.model.Person;
import pl.shonsu.restapi.service.PersonService;

import java.util.List;

@RestController
public class PersonController {

    @GetMapping("/persons")
    public List<Person> getPerson() {
        try {
            throw new IllegalAccessException("Not implemented yet");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/persons/{id}")
    public List<Person> getSinglePerson(@PathVariable long id) {
        try {
            throw new IllegalAccessException("Not implemented yet");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
