package pl.shonsu.restapi.controller;

import org.springframework.data.domain.Sort;
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
    public List<PersonDto> getPerson(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page > 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return PersonDtoMapper.mapToPersonDtos(personService.getPersons(pageNumber, sortDirection));
    }

    @GetMapping("/persons/adresses")
    public List<Person> getPersonsWithAdresses(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page > 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return personService.getPersonsWithAdresses(pageNumber, sortDirection);
    }

    @GetMapping("/persons/{id}")
    public Person getSinglePerson(@PathVariable long id) {
        return personService.getSinglePerson(id);
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

}
