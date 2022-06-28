package pl.shonsu.restapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.shonsu.restapi.controller.dto.AdressDto;
import pl.shonsu.restapi.controller.dto.PersonDto;
import pl.shonsu.restapi.controller.mapper.PersonMapper;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.shonsu.restapi.controller.mapper.AdressMapper.mapToAdress;
import static pl.shonsu.restapi.controller.mapper.PersonDtoMapper.mapToPersonDtoWithAdresses;
import static pl.shonsu.restapi.controller.mapper.PersonMapper.mapToPerson;
import static pl.shonsu.restapi.controller.mapper.PersonMapper.mapToPersons;

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
        PersonDto personDto = dummyService.getDummyPerson();
        personService.addPerson(mapToPerson(EMPTY_ID, personDto));
        return personDto;
    }

    @Transactional
    public Set<PersonDto> addDummyPersons(Integer number) {
        Set<PersonDto> personList;
        Supplier<PersonDto> s = () -> dummyService.getDummyPerson();
        personList = Stream.generate(s).limit(number).collect(Collectors.toSet());
        Set<Person> persons = personList.stream()
                .map(personDto -> mapToPerson(EMPTY_ID, personDto)).collect(Collectors.toSet());
        personService.addPersons(persons);
        return personList;
    }

    @Transactional
    public PersonDto addDummyPersonWithAdress() {
        PersonDto personDto = dummyService.getDummyPersonWithAdress();
        Person person = PersonMapper.mapToPersonWithAdresses(personDto);
        person = personService.addPerson(person);
        personDto = mapToPersonDtoWithAdresses(person, person.getAdresses());
        return personDto;
    }


    @Transactional
    public Set<PersonDto> addPersonsWithAdress(Integer numberOfPersons) {
        Set<PersonDto> personList;
        Supplier<PersonDto> s = () -> dummyService.getDummyPersonWithAdress();
        personList = Stream.generate(s).limit(numberOfPersons).collect(Collectors.toSet());
        personList = personService.addPersons(mapToPersons(personList));
        return personList;
    }

    @Transactional
    public PersonDto addAdressToPersonWithNullAdress() {
        Person person = personService.getPersonWithAdressNull();
        AdressDto adressDto = dummyService.getDummyAdress();
        person.addAdress(mapToAdress(EMPTY_ID, adressDto));
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
