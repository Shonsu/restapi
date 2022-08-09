package pl.shonsu.adressbook.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.shonsu.adressbook.controller.mapper.PersonDtoMapper;
import pl.shonsu.adressbook.exceptionshandling.exceptions.PersonNotFoundException;
import pl.shonsu.adressbook.model.Adress;
import pl.shonsu.adressbook.model.Person;
import pl.shonsu.adressbook.repository.AdressRepository;
import pl.shonsu.adressbook.repository.PersonRepository;
import pl.shonsu.adressbook.controller.dto.PersonDto;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
public class FakerService {
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
        return PersonDtoMapper.mapToPersonDto(personRepository.save(person));
    }

    @Transactional
    public List<PersonDto> addDummyPersons(Integer number) {
        List<Person> personList;
        Supplier<Person> s = () -> dummyService.getDummyPerson();
        personList = Stream.generate(s).limit(number).toList();
        personService.addPersons(personList);
        return PersonDtoMapper.mapToPersonsDtoWithAdresses(personList);
    }

    @Transactional
    public PersonDto addDummyPersonWithAdress() {
        Person person = dummyService.getDummyPersonWithAdress();
        person = personRepository.save(person);
        return PersonDtoMapper.mapToPersonDtoWithAdresses(person, person.getAdresses());

    }

    @Transactional
    public List<PersonDto> addDummyPersonsWithAdress(Integer numberOfPersons) {
        Supplier<Person> s = () -> dummyService.getDummyPersonWithAdress();
        List<Person> personList = Stream.generate(s).limit(numberOfPersons).toList();
        personList = personService.addPersons(personList);
        return PersonDtoMapper.mapToPersonsDtoWithAdresses(personList);// personList;
    }

    @Transactional
    public PersonDto addAdressToPersonWithNullAdress() {
        Person person = personRepository.findFirstPersonByAdressesIdIsNull()
                .orElseThrow(() -> new PersonNotFoundException("Can't find person without adress"));
        Adress adress = dummyService.getDummyAdress();
        person.addAdress(adress);
        person = personRepository.save(person);
        return PersonDtoMapper.mapToPersonDtoWithAdresses(person, person.getAdresses());
    }

    @Transactional
    public PersonDto bindAdressToPerson(Long personId, Long adressId) {
        Person person = personRepository.getReferenceById(personId);
        Adress adress = adressRepository.getReferenceById(adressId);
        person.addAdress(adress);
        person = personRepository.save(person);
        return PersonDtoMapper.mapToPersonDtoWithAdresses(person, person.getAdresses());
    }
}
