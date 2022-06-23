package pl.shonsu.restapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.shonsu.restapi.controller.dto.AdressDto;
import pl.shonsu.restapi.controller.dto.AdressDtoMapper;
import pl.shonsu.restapi.controller.dto.PersonDto;
import pl.shonsu.restapi.controller.dto.PersonDtoMapper;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;


@Service
public class FakerService {

    DummyService dummyService;
    PersonService personService;
    AdressService adressService;

    public FakerService(DummyService dummyService, PersonService personService, AdressService adressService) {
        this.dummyService = dummyService;
        this.personService = personService;
        this.adressService = adressService;
    }

    @Transactional
    public Person addDummyPerson() {
        return personService.addPerson(dummyService.getDummyPerson());
    }
    @Transactional
    public List<Person> addDummyPersons(Integer number) {
        List<Person> personList;
        Supplier<Person> s = () -> dummyService.getDummyPerson();
        personList = Stream.generate(s).limit(number).toList();
        return personService.addPersons(personList);
    }
    @Transactional
    public PersonDto addDummyPersonWithAdress() {
        Person person = dummyService.getDummyPersonWithAdress();
        person = personService.addPerson(person);
        return PersonDtoMapper.mapToPersonDtoWithAdress(person, person.getAdresses().iterator().next());
    }
    @Transactional
    public List<PersonDto> addPersonsWithAdress(Integer numberOfPersons) {
        List<Person> personList;
        Supplier<Person> s = () -> dummyService.getDummyPersonWithAdress();
        personList = Stream.generate(s).limit(numberOfPersons).toList();
        personList = personService.addPersons(personList);
        return personList.stream()
                .map(person -> PersonDtoMapper.mapToPersonDtoWithAdress(person, person.getAdresses().iterator().next()))
                .toList();
    }
    @Transactional
    public AdressDto addAdressToPersonWithNullAdress() {
        Person person = personService.getPersonWithAdressNull();
        Adress adress = dummyService.getDummyAdress();
        adress.addPerson(person);
        adress = adressService.addAdress(adress);
        return AdressDtoMapper.mapToAdressDtoWithPerson(adressService.addAdress(adress), person);
    }
    @Transactional
    public PersonDto bindAdressToPerson(Long personId, Long adressId) {
        Person person = personService.getPersonById(personId);
        Adress adress = adressService.getAdressById(adressId);
        person.addAdress(adress);
        person = personService.addPerson(person);
        return PersonDtoMapper.mapToPersonDtoWithAdress(person, adress);
    }
}
