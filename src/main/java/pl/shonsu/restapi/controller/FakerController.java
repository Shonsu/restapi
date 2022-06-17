package pl.shonsu.restapi.controller;

import org.springframework.web.bind.annotation.*;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;
import pl.shonsu.restapi.service.AdressService;
import pl.shonsu.restapi.service.DummyPersonService;
import pl.shonsu.restapi.service.PersonService;

import java.util.List;

@RequestMapping("/api/faker")
@RestController
public class FakerController {
    PersonService personService;

    AdressService adressService;

    DummyPersonService dummyPersonService;
    public FakerController(PersonService personService, AdressService adressService, DummyPersonService dummyPersonService) {
        this.personService = personService;
        this.adressService = adressService;
        this.dummyPersonService = dummyPersonService;
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        // Adress adress = dummyPersonService.getDummyAdress(adress.getId(), List.of(person));
        person = dummyPersonService.getDummyPerson(person.getId(), List.of());
        return personService.addPerson(person);
    }
    @PostMapping("/adress")
    public Adress addPerson(@RequestBody Adress adress) {
        Person person = personService.getPersonWithAdressNull();
        adress = dummyPersonService.getDummyAdress(adress.getId(), List.of(person));
        return adressService.addAdress(adress);
    }

}
