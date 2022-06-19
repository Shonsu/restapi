package pl.shonsu.restapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.shonsu.restapi.controller.dto.AdressDto;
import pl.shonsu.restapi.controller.dto.AdressDtoMapper;
import pl.shonsu.restapi.controller.dto.PersonDto;
import pl.shonsu.restapi.controller.dto.PersonDtoMapper;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;
import pl.shonsu.restapi.service.AdressService;
import pl.shonsu.restapi.service.DummyPersonService;
import pl.shonsu.restapi.service.PersonService;

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
    public PersonDto addPersonWithAdress() {
        Person person = dummyPersonService.getDummyPersonWithAdress();
        person = personService.addPerson(person);
        AdressDto adressDto = AdressDtoMapper.mapToAdressDto(person.getAdresses().get(0));
        return PersonDtoMapper.mapToPersonDtoWithAdress(person, adressDto);
    }
    @PostMapping("/adressWithPersonWithNullAdress")
    public AdressDto addAdress() {
        Person person = personService.getPersonWithAdressNull();
        Adress adress1 = dummyPersonService.getDummyAdress();
        adress1.addPerson(person);
        adress1 = adressService.addAdress(adress1);
        PersonDto personDto = PersonDtoMapper.mapToPersonDto(person);
        return AdressDtoMapper.mapToAdressDtoWithPerson(adressService.addAdress(adress1), personDto);

    }
}
