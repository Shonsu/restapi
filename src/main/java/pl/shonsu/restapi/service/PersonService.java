package pl.shonsu.restapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.shonsu.restapi.controller.dto.PersonDto;
import pl.shonsu.restapi.controller.dto.PersonRequestDto;
import pl.shonsu.restapi.controller.mapper.PersonDtoMapper;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;
import pl.shonsu.restapi.repository.AdressRepository;
import pl.shonsu.restapi.repository.PersonRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.shonsu.restapi.controller.mapper.PersonDtoMapper.*;
import static pl.shonsu.restapi.controller.mapper.PersonMapper.mapToPersonFromPersonRequestDto;

@Service
public class PersonService {

    private static final int PAGE_SIZE = 20;
    private static final Long EMPTY_ID = null;
    private final PersonRepository personRepository;
    private final AdressRepository adressRepository;

    public PersonService(PersonRepository personRepository, AdressRepository adressRepository) {
        this.personRepository = personRepository;
        this.adressRepository = adressRepository;
    }

    public Page<PersonDto> getPersons(int page, Sort.Direction sort) {
        return personRepository.findAllPersons(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id"))).map(PersonDtoMapper::mapToPersonDto);
    }

    public Page<PersonDto> getPersonsWithAdresses(int page, Sort.Direction sort) {
        Page<Person> allPersons = personRepository.findAllPersons(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")));
        List<Long> ids = allPersons.stream()
                .map(Person::getId)
                .toList();
        Set<Adress> adresses = adressRepository.findAllAdressesByPersonsIdIn(ids);
        allPersons.forEach(person -> person.setAdresses(extractAdresses(adresses, person.getId()))); //todo move to adress class
        return allPersons.map(person -> PersonDtoMapper.mapToPersonDtoWithAdresses(
                person,
                person.getAdresses()));
    }

    private Set<Adress> extractAdresses(Set<Adress> adresses, long id) {
        return adresses.stream().
                filter(adress -> adress.getPersons().stream()
                        .anyMatch(person -> person.getId() == id))
                .collect(Collectors.toSet());
    }

    @Transactional
    public PersonDto addPerson(PersonRequestDto personRequestDto) {
        Person person = mapToPersonFromPersonRequestDto(EMPTY_ID, personRequestDto);
        return mapToPersonDto(personRepository.save(person));
    }

    @Transactional
    public List<Person> addPersons(List<Person> persons) {
        persons = personRepository.saveAll(persons);
        return persons;
    }

    public PersonDto getPersonWithAdressNull() {
        return mapToPersonDto(personRepository.findFirstPersonByAdressesIdIsNull());
    }

    public PersonDto getPersonById(Long id) {
        Person person =  personRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find person by given id " + id));
        return mapToPersonDto(person);
    }

    public Set<PersonDto> getPersonsWithoutAdresses() {
        return mapToPersonsDto(new HashSet<>(personRepository.findByAdressesIsNull()));
    }

    public Person getReferenceById(Long id) {
        return personRepository.getReferenceById(id);
    }

    public PersonDto getPersonByIdWithAdresses(long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find person by given id " + id));
        return mapToPersonDtoWithAdresses(person, person.getAdresses());
    }

    public PersonDto updatePerson(Long id, PersonRequestDto personRequestDto) {
        Person person = personRepository.getReferenceById(id);
        person = personRepository.save(mapToPersonFromPersonRequestDto(person.getId(), personRequestDto));
        return mapToPersonDto(person);
    }
}
