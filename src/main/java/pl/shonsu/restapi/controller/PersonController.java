package pl.shonsu.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.shonsu.restapi.model.Person;
import pl.shonsu.restapi.service.PersonService;

import java.util.List;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public List<Person> getPerson() {
        return personService.getPersons();
    }

    @GetMapping("/persons/{id}")
    public Person getSinglePerson(@PathVariable long id) {
        return personService.getSinglePerson(id);
    }

}
