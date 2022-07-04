package pl.shonsu.restapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.shonsu.restapi.controller.dto.PersonDto;
import pl.shonsu.restapi.controller.dto.PersonRequestDto;
import pl.shonsu.restapi.service.PersonService;

import java.util.Set;

@RequestMapping("/api/doc")
@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    @ResponseStatus(HttpStatus.OK)
    public Page<PersonDto> getPersons(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page > 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return personService.getPersons(pageNumber, sortDirection);
    }

    @GetMapping("/persons/adresses")
    @ResponseStatus(HttpStatus.OK)
    public Page<PersonDto> getPersonsWithAdresses(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page > 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return personService.getPersonsWithAdresses(pageNumber, sortDirection);
    }

    @PostMapping("/person")
    public PersonDto createPerson(@RequestBody PersonRequestDto personRequestDto) {
        return personService.addPerson(personRequestDto);
    }

    @GetMapping("/persons/{id}")
    //@ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonById(@PathVariable long id) {
        return personService.getPersonById(id);
    }

    @PutMapping("/persons/{id}")
    public PersonDto updatePerson(@PathVariable Long id, @RequestBody PersonRequestDto personRequestDto) {
        return personService.updatePerson(id, personRequestDto);
    }

    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable long id) {
        personService.deletePerson(id);
    }

    @GetMapping("/person/{id}/adresses")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonWithAdressesById(@PathVariable long id) {
        return personService.getPersonWithAdressesById(id);
    }

    @GetMapping("/firstPersonWithNullAdress")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonWithAdressNull() {
        return personService.getPersonWithAdressNull();
    }

    @GetMapping("/personsWithoutAdresses")
    //@ResponseStatus(HttpStatus.OK)
    public Set<PersonDto> getPersonsWithoutAdresses() {
        return personService.getPersonsWithoutAdresses();
    }
}
