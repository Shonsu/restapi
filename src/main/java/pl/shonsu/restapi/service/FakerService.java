package pl.shonsu.restapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.shonsu.restapi.controller.dto.PersonDto;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static pl.shonsu.restapi.controller.mapper.PersonDtoMapper.*;

@Service
public class FakerService {

    private static final Long EMPTY_ID = null;
    DummyService dummyService;
    PersonService personService;
    AdressService adressService;

    public FakerService(DummyService dummyService, PersonService personService, AdressService adressService) {
        this.dummyService = dummyService;
        this.personService = personService;
        this.adressService = adressService;
    }

    @Transactional
    public PersonDto addDummyPerson() {
        Person person = dummyService.getDummyPerson();
        return mapToPersonDto(personService.addPerson(person));
    }

    @Transactional
    public List<PersonDto> addDummyPersons(Integer number) {
        List<Person> personList;
        Supplier<Person> s = () -> dummyService.getDummyPerson();
        personList = Stream.generate(s).limit(number).toList();
        personService.addPersons(personList);
        return mapToPersonsDtoWithAdresses(personList);
    }

    @Transactional
    public PersonDto addDummyPersonWithAdress() {
        Person person = dummyService.getDummyPersonWithAdress();
        person = personService.addPerson(person);
        return mapToPersonDtoWithAdresses(person, person.getAdresses());

    }

    @Transactional
    public List<PersonDto> addDummyPersonsWithAdress(Integer numberOfPersons) {
        Supplier<Person> s = () -> dummyService.getDummyPersonWithAdress();
        List<Person> personList = Stream.generate(s).limit(numberOfPersons).toList();
        personList = personService.addPersons(personList);
        return mapToPersonsDtoWithAdresses(personList);// personList;
    }

    @Transactional
    public PersonDto addAdressToPersonWithNullAdress() {
        Person person = personService.getPersonWithAdressNull();
        Adress adress = dummyService.getDummyAdress();
        person.addAdress(adress);
        person = personService.addPerson(person);
        return mapToPersonDtoWithAdresses(person, person.getAdresses());
    }

    @Transactional
    public PersonDto bindAdressToPerson(Long personId, Long adressId) {
        Person person = personService.getPersonById(personId);
        Adress adress = adressService.getAdressById(adressId);
        person.addAdress(adress);
        person = personService.addPerson(person);
        return mapToPersonDtoWithAdresses(person, person.getAdresses());
    }
}
