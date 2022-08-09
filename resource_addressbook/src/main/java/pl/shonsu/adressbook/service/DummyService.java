package pl.shonsu.adressbook.service;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;
import pl.shonsu.adressbook.model.Adress;
import pl.shonsu.adressbook.model.Person;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

@Component
public class DummyService {
    private Faker faker;

    @PostConstruct
    private void fill() {
        this.faker = generateFaker();
    }

    private Faker generateFaker() {
        return new Faker(new Locale("pl-PL"));
    }

    public Person getDummyPerson() {
        Date date = faker.date().birthday(10, 70);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new Person(
                faker.name().firstName(),
                faker.name().lastName(),
                localDate);
    }

    public Person getDummyPersonWithAdress() {
        Person person = getDummyPerson();
        Adress adress = getDummyAdress();
        person.addAdress(adress);
        return person;
    }

    public Adress getDummyAdress() {
        return new Adress(
                faker.address().city(),
                faker.address().streetName(),
                faker.address().buildingNumber(),
                faker.number().numberBetween(1, 1000));
    }
}
