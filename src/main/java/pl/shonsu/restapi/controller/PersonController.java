package pl.shonsu.restapi.controller;

import org.springframework.web.bind.annotation.*;
import pl.shonsu.restapi.controller.dto.PersonDto;
import pl.shonsu.restapi.controller.dto.PersonDtoMapper;
import pl.shonsu.restapi.model.Person;
import pl.shonsu.restapi.service.PersonService;

import java.util.List;

@RequestMapping("/api/doc")
@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public List<PersonDto> getPerson(@RequestParam(required = false) int page) {
        // int pageNumber = page > 0 ? page : 1;
        return PersonDtoMapper.mapToPersonDtos(personService.getPersons(page));
    }

    @GetMapping("/persons/adresses")
    public List<Person> getPersonsWithAdresses(@RequestParam(required = false) int page) {
        return personService.getPersonsWithAdresses(page);
    }

    @GetMapping("/persons/{id}")
    public Person getSinglePerson(@PathVariable long id) {
        return personService.getSinglePerson(id);
    }

}
