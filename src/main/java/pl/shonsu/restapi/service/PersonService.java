package pl.shonsu.restapi.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;
import pl.shonsu.restapi.repository.AdressRepository;
import pl.shonsu.restapi.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private static final int PAGE_SIZE = 20;
    private final PersonRepository personRepository;
    private final AdressRepository adressRepository;


    public PersonService(PersonRepository personRepository, AdressRepository adressRepository) {
        this.personRepository = personRepository;
        this.adressRepository = adressRepository;
    }

    public List<Person> getPersons(int page) {
        return personRepository.findAllPerson(PageRequest.of(page, PAGE_SIZE));
    }

    public Person getSinglePerson(long id) {
        return personRepository.findById(id).orElseThrow();
    }

    public List<Person> getPersonsWithAdresses(int page) {
        List<Person> allPersons = personRepository.findAllPerson(PageRequest.of(page, PAGE_SIZE));
        List<Long> ids = allPersons.stream()
                .map(Person::getId)
                .toList();
        List<Adress> adresses = adressRepository.findAllByPostIdIn(ids);
        allPersons.stream()
                .map(person -> person.setAdresses(extractAdresses(adresses, person.getId())))
                .toList();


    }

    private List<Adress> extractAdresses(List<Adress> adresses, long id) {
        return adresses.stream()
                .filter(adress -> adress.getId() == id)
                .toList();
    }
}
