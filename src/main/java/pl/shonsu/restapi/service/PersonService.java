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

@Transactional
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

    public List<PersonDto> getPersonsWithAdresses(int page, Sort.Direction sort) {
        List<Person> allPersons = personRepository.findAllPersons(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")));
        List<Long> ids = allPersons.stream()
                .map(Person::getId)
                .toList();
        List<Adress> adresses = adressRepository.findAllAdressesByPersonsIdIn(ids);

        allPersons.forEach(person -> person.setAdresses(extractAdresses(adresses, person.getId())));
        return allPersons.stream()
                .map(person -> PersonDtoMapper.mapToPersonDtoWithAdresses(person, AdressDtoMapper.mapToAdressDtos(person.getAdresses())))
                .toList();
    }

    private List<Adress> extractAdresses(List<Adress> adresses, long id) {
        return adresses.stream().filter(adress -> adress.getPersons().stream().anyMatch(person -> person.getId() == id)).toList();
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public Person getPersonWithAdressNull() {
        return personRepository.findFirstPersonByAdressesIdIsNull();
    }
}
