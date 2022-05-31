package pl.shonsu.restapi.service;

import org.springframework.stereotype.Service;
import pl.shonsu.restapi.model.Person;
import pl.shonsu.restapi.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public Person getSinglePerson(long id) {
        return personRepository.findById(id).orElseThrow();
    }
}
