package pl.shonsu.adressbook.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.shonsu.adressbook.controller.dto.PersonDto;
import pl.shonsu.adressbook.controller.dto.PersonRequestDto;
import pl.shonsu.adressbook.service.PersonService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Set;

@Validated
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

    @PostMapping("/persons")
    public PersonDto createPerson(@RequestBody @Valid PersonRequestDto personRequestDto) {
        return personService.addPerson(personRequestDto);
    }

    @GetMapping("/persons/{id}")
    public PersonDto getPersonById(@PathVariable @Positive long id) {
        return personService.getPersonById(id);
    }

    @PutMapping("/persons/{id}")
    public PersonDto updatePerson(@PathVariable @Positive Long id, @RequestBody @Valid PersonRequestDto personRequestDto) {
        return personService.updatePerson(id, personRequestDto);
    }

    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable @Positive long id) {
        personService.deletePerson(id);
    }

    @GetMapping("/persons/{id}/adresses")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonWithAdressesById(@PathVariable @Positive long id) {
        return personService.getPersonWithAdressesById(id);
    }

    @GetMapping("/firstPersonWithNullAdress")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonWithAdressNull() {
        return personService.getPersonWithAdressNull();
    }

    @GetMapping("/personsWithoutAdresses")
    public Set<PersonDto> getPersonsWithoutAdresses() {
        return personService.getPersonsWithoutAdresses();
    }
}
