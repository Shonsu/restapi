package pl.shonsu.restapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.shonsu.restapi.controller.dto.AdressDto;
import pl.shonsu.restapi.controller.dto.PersonDto;
import pl.shonsu.restapi.model.Person;
import pl.shonsu.restapi.service.AdressService;
import pl.shonsu.restapi.service.FakerService;
import pl.shonsu.restapi.service.PersonService;

import java.util.List;
import java.util.Set;

@RequestMapping("/api/faker")
@RestController
public class FakerController {
    PersonService personService;
    AdressService adressService;
    FakerService fakerService;

    public FakerController(PersonService personService, AdressService adressService, FakerService fakerService) {
        this.personService = personService;
        this.adressService = adressService;
        this.fakerService = fakerService;
    }

    @PostMapping("/person")
    public PersonDto addPerson() {
        return fakerService.addDummyPerson();
    }

    @PostMapping("/persons")
    public Set<PersonDto> addPersons(@RequestParam(required = false) Integer number) {
        number = number > 0 ? number : 1;
        return fakerService.addDummyPersons(number);
    }

    @PostMapping("/personWithAdress")
    public PersonDto addPersonWithAdress() {
        return fakerService.addDummyPersonWithAdress();
    }

    @PostMapping("/personsWithAdress")
    public Set<PersonDto> addPersonsWithAdress(@RequestParam(required = false) Integer numberOfPersons) {
        numberOfPersons = numberOfPersons > 0 ? numberOfPersons : 1;
        return fakerService.addPersonsWithAdress(numberOfPersons);
    }

    @PostMapping("/adressToPersonWithNullAdress")
    public PersonDto addAdressToPersonWithNullAdress() {
        return fakerService.addAdressToPersonWithNullAdress();
    }

    @PostMapping("/bindAdressToPerson")
    public PersonDto bindAdressToPerson(@RequestParam Long personId, Long adressId) {
        return fakerService.bindAdressToPerson(personId,adressId);
    }

}
