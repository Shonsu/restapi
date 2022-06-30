package pl.shonsu.restapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.shonsu.restapi.controller.dto.PersonDto;
import pl.shonsu.restapi.service.AdressService;
import pl.shonsu.restapi.service.FakerService;
import pl.shonsu.restapi.service.PersonService;

import java.util.List;

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
    @SuppressWarnings("unused")
    public PersonDto addPerson() {
        return fakerService.addDummyPerson();
    }

    @PostMapping("/persons")
    @SuppressWarnings("unused")
    public List<PersonDto> addPersons(@RequestParam(required = false) Integer number) {
        number = number > 0 ? number : 1;
        return fakerService.addDummyPersons(number);
    }

    @PostMapping("/personWithAdress")
    @SuppressWarnings("unused")
    public PersonDto addPersonWithAdress() {
        return fakerService.addDummyPersonWithAdress();
    }

    @PostMapping("/personsWithAdress")
    @SuppressWarnings("unused")
    public List<PersonDto> addPersonsWithAdress(@RequestParam(required = false) Integer numberOfPersons) {
        numberOfPersons = numberOfPersons > 0 ? numberOfPersons : 1;
        return fakerService.addDummyPersonsWithAdress(numberOfPersons);
    }

    @PostMapping("/adressToPersonWithNullAdress")
    @SuppressWarnings("unused")
    public PersonDto addAdressToPersonWithNullAdress() {
        return fakerService.addAdressToPersonWithNullAdress();
    }

    @PostMapping("/bindAdressToPerson")
    @SuppressWarnings("unused")
    public PersonDto bindAdressToPerson(@RequestParam Long personId, Long adressId) {
        return fakerService.bindAdressToPerson(personId,adressId);
    }

}
