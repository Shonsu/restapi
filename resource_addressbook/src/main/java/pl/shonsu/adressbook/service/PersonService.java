package pl.shonsu.adressbook.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.shonsu.adressbook.controller.dto.PersonRequestDto;
import pl.shonsu.adressbook.controller.mapper.PersonDtoMapper;
import pl.shonsu.adressbook.exceptionshandling.exceptions.PersonNotFoundException;
import pl.shonsu.adressbook.repository.PersonRepository;
import pl.shonsu.adressbook.controller.dto.PersonDto;
import pl.shonsu.adressbook.model.Adress;
import pl.shonsu.adressbook.model.Person;
import pl.shonsu.adressbook.repository.AdressRepository;
import pl.shonsu.adressbook.controller.mapper.PersonMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        Person person = PersonMapper.mapToPersonFromPersonRequestDto(EMPTY_ID, personRequestDto);
        return PersonDtoMapper.mapToPersonDto(personRepository.save(person));
    }

    @Transactional
    public List<Person> addPersons(List<Person> persons) {
        persons = personRepository.saveAll(persons);
        return persons;
    }

    public PersonDto getPersonWithAdressNull() {
        Person person = personRepository.findFirstPersonByAdressesIdIsNull()
                .orElseThrow(() -> new PersonNotFoundException("Can't find person without adress"));
        return PersonDtoMapper.mapToPersonDto(person);
    }

    public PersonDto getPersonById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        return PersonDtoMapper.mapToPersonDto(person);
    }

    public Set<PersonDto> getPersonsWithoutAdresses() {
        List<Person> personList = personRepository.findByAdressesIsNull();
        if (personList.isEmpty()) throw new PersonNotFoundException("Can't find person without adress");
        return PersonDtoMapper.mapToPersonsDto(new HashSet<>(personList));
    }

    public PersonDto getPersonWithAdressesById(long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        return PersonDtoMapper.mapToPersonDtoWithAdresses(person, person.getAdresses());
    }

    @Transactional
    public PersonDto updatePerson(Long id, PersonRequestDto personRequestDto) {
        Person person = personRepository.findById(id).
                orElseThrow(() -> new PersonNotFoundException(id));
        person = personRepository.save(PersonMapper.mapToPersonFromPersonRequestDto(person.getId(), personRequestDto));
        return PersonDtoMapper.mapToPersonDto(person);
    }

    public void deletePerson(long id) {
        if (!personRepository.existsById(id)) throw new PersonNotFoundException(id);
        personRepository.deleteById(id);
    }
}
