package pl.shonsu.restapi.service;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
public class DummyPersonService {
    private Faker faker;

    private void fill() {
        this.faker = generateFaker();
    }

    private Faker generateFaker() {
        return new Faker(new Locale("pl-PL"));
    }

    public Person getDummyPerson(Long id, List<Adress> adresses) {
        Date date = faker.date().birthday(10,70);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new Person(id, faker.name().firstName(), faker.name().lastName(), adresses, localDate);
    }
}
