package pl.shonsu.restapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.shonsu.restapi.controller.dto.PersonDto;
import pl.shonsu.restapi.model.Person;
import pl.shonsu.restapi.service.PersonService;

import java.util.List;

import static pl.shonsu.restapi.controller.mapper.PersonMapper.mapToPerson;

@RequestMapping("/api/doc")
@RestController
public class PersonController {

    private static final Long EMPTY_ID = null;
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public Page<Person> getPersons(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page > 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        Page<Person> persons = personService.getPersons(pageNumber, sortDirection);
        persons.map(PersonDto::new);
        return persons;
    }

//    @GetMapping("/persons2")
//    public List<PersonDto> getPersons2(@RequestParam(required = false) Integer page, Sort.Direction sort) {
//        int pageNumber = page != null && page > 0 ? page : 0;
//        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
//        return personService.getPersons2(pageNumber, sortDirection);
//    }
    @GetMapping("/persons/adresses")
    public List<PersonDto> getPersonsWithAdresses(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page > 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return personService.getPersonsWithAdresses(pageNumber, sortDirection);
    }

    @GetMapping("/persons/{id}")
    public Person getPersonById(@PathVariable long id) {
        return personService.getPersonById(id);
    }

    @PostMapping("/person")
    public Person createPerson(@RequestBody PersonDto personDto) {
        Person person = mapToPerson(EMPTY_ID, personDto);
        return personService.addPerson(person);
    }

    @PutMapping("/person/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        Person person = mapToPerson(id, personDto);
        return personService.addPerson(person);
    }

    @GetMapping("/firstPersonWithNullAdress")
    public Person getPersonWithAdressNull() {
        return personService.getPersonWithAdressNull();
    }
}
