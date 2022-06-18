package pl.shonsu.restapi.controller;

import org.springframework.web.bind.annotation.*;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;
import pl.shonsu.restapi.service.AdressService;
import pl.shonsu.restapi.service.DummyPersonService;
import pl.shonsu.restapi.service.PersonService;

import java.util.List;
import java.util.Set;

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
    public Person addPerson() {

        Person person1 = dummyPersonService.getDummyPerson();
        return personService.addPerson(person1);
    }
    @PostMapping("/personWithAdress")
    public Person addPersonWithAdress() {

        Adress adress1 = dummyPersonService.getDummyAdress();
        Person person1 = dummyPersonService.getDummyPerson();
        person1.addAdress(adress1);
        return personService.addPerson(person1);
    }
    @PostMapping("/adress")
    public Adress addAdress() {
        Person person = personService.getPersonWithAdressNull();
        Adress adress1 = dummyPersonService.getDummyAdress();
        adress1.addPerson(person);
        return adressService.addAdress(adress1);
    }
}
