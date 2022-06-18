package pl.shonsu.restapi.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;
import pl.shonsu.restapi.repository.AdressRepository;
import pl.shonsu.restapi.repository.PersonRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<Person> getPersonsWithAdresses(int page, Sort.Direction sort) {
        List<Person> allPersons = personRepository.findAllPersons(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")));
        List<Long> ids = allPersons.stream()
                .map(Person::getId)
                .toList();
        ids.forEach(System.out::println);
        List<Adress> adresses = adressRepository.findAllAdressesByPersonsIdIn(ids);
        //adresses.forEach(System.out::println);
        allPersons.forEach(person -> person.setAdresses(extractAdresses(adresses, person.getId())));
        return allPersons;
    }

    private List<Adress> extractAdresses(List<Adress> adresses, long id) {
       // List<Long> ids = adresses.stream().map(Adress::getPersons).toList();
        return adresses.stream()
                .filter(adress -> adress.getId() == id)
                .toList();
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public Person getPersonWithAdressNull() {
        return personRepository.findFirstPersonByAdressesIdIsNull();
    }
}
