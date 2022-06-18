package pl.shonsu.restapi.service;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Component
public class DummyPersonService {
    private Faker faker;

    @PostConstruct
    private void fill() {
        this.faker = generateFaker();
    }

    private Faker generateFaker() {
        return new Faker(new Locale("pl-PL"));
    }

    public Person getDummyPerson(){
        Date date = faker.date().birthday(10, 70);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new Person(
                faker.name().firstName(),
                faker.name().lastName(),
                localDate);
    }

    public Adress getDummyAdress() {
        return new Adress(
                faker.address().city(),
                faker.address().streetName(),
                faker.address().buildingNumber(),
                faker.number().numberBetween(1, 1000));
    }

}
