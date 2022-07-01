package pl.shonsu.restapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.shonsu.restapi.controller.dto.PersonDto;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;
import pl.shonsu.restapi.repository.AdressRepository;
import pl.shonsu.restapi.repository.PersonRepository;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static pl.shonsu.restapi.controller.mapper.PersonDtoMapper.*;

@Service
public class FakerService {

   // private static final Long EMPTY_ID = null;
    DummyService dummyService;
    PersonService personService;
    AdressService adressService;


    private final PersonRepository personRepository;
    private final AdressRepository adressRepository;

    public FakerService(DummyService dummyService, PersonService personService, AdressService adressService, PersonRepository personRepository, AdressRepository adressRepository) {
        this.dummyService = dummyService;
        this.personService = personService;
        this.adressService = adressService;
        this.personRepository = personRepository;
        this.adressRepository = adressRepository;
    }

    @Transactional
    public PersonDto addDummyPerson() {
        Person person = dummyService.getDummyPerson();
        return mapToPersonDto(personRepository.save(person));
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
        person = personRepository.save(person);
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
        Person person = personRepository.findFirstPersonByAdressesIdIsNull();
        Adress adress = dummyService.getDummyAdress();
        person.addAdress(adress);
        person = personRepository.save(person);
        return mapToPersonDtoWithAdresses(person, person.getAdresses());
    }

    @Transactional
    public PersonDto bindAdressToPerson(Long personId, Long adressId) {
        Person person = personRepository.getReferenceById(personId);
        Adress adress = adressRepository.getReferenceById(adressId);
        person.addAdress(adress);
        person = personRepository.save(person);
        return mapToPersonDtoWithAdresses(person, person.getAdresses());
    }
}
