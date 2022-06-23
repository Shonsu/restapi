package pl.shonsu.restapi.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.shonsu.restapi.controller.dto.AdressDtoMapper;
import pl.shonsu.restapi.controller.dto.PersonDto;
import pl.shonsu.restapi.controller.dto.PersonDtoMapper;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;
import pl.shonsu.restapi.repository.AdressRepository;
import pl.shonsu.restapi.repository.PersonRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private static final int PAGE_SIZE = 20;
    private final PersonRepository personRepository;
    private final AdressRepository adressRepository;


    public PersonService(PersonRepository personRepository, AdressRepository adressRepository) {
        this.personRepository = personRepository;
        this.adressRepository = adressRepository;
    }

    public List<Person> getPersons(int page, Sort.Direction sort) {
        return personRepository.findAllPersons(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")));
    }

    public Person getSinglePerson(long id) {
        return personRepository.findById(id).orElseThrow();
    }

    public Set<PersonDto> getPersonsWithAdresses(int page, Sort.Direction sort) {
        List<Person> allPersons = personRepository.findAllPersons(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")));
        List<Long> ids = allPersons.stream()
                .map(Person::getId)
                .toList();
        Set<Adress> adresses = adressRepository.findAllAdressesByPersonsIdIn(ids);

        allPersons.forEach(person -> person.setAdresses(extractAdresses(adresses, person.getId())));
        return allPersons.stream()
                .map(person -> PersonDtoMapper.mapToPersonDtoWithAdresses(
                        person,
                        AdressDtoMapper.mapToAdressDtos(person.getAdresses())))//TODO move to Adress class
                .collect(Collectors.toSet());
    }

    private Set<Adress> extractAdresses(Set<Adress> adresses, long id) {
        return adresses.stream().
                filter(adress -> adress.getPersons().stream()
                        .anyMatch(person -> person.getId() == id)).collect(Collectors.toSet());
    }
    @Transactional

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }
    @Transactional

    public List<Person> addPersons(List<Person> persons) {
        return personRepository.saveAll(persons);
    }

    public Person getPersonWithAdressNull() {
        return personRepository.findFirstPersonByAdressesIdIsNull();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find person by given id " + id));
    }
}
