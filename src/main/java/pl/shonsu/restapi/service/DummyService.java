package pl.shonsu.restapi.service;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;
import pl.shonsu.restapi.controller.dto.AdressDto;
import pl.shonsu.restapi.controller.dto.PersonDto;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

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

    public PersonDto getDummyPerson() {
        Date date = faker.date().birthday(10, 70);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new PersonDto(
                faker.name().firstName(),
                faker.name().lastName(),
                localDate);
    }
    public PersonDto getDummyPersonWithAdress() {
        PersonDto personWithAdress = getDummyPerson();
        AdressDto adress = getDummyAdress();
        personWithAdress.setAdressesDto(Set.of(adress));
        return personWithAdress;
    }
    public AdressDto getDummyAdress() {
        return new AdressDto(
                faker.address().city(),
                faker.address().streetName(),
                faker.address().buildingNumber(),
                faker.number().numberBetween(1, 1000));
    }
}
