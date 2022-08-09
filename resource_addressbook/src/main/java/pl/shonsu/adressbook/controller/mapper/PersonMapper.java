package pl.shonsu.adressbook.controller.mapper;

import pl.shonsu.adressbook.controller.dto.PersonRequestDto;
import pl.shonsu.adressbook.controller.dto.PersonDto;
import pl.shonsu.adressbook.model.Person;

import java.util.Set;
import java.util.stream.Collectors;

import static pl.shonsu.adressbook.controller.mapper.AdressMapper.mapToAdresses;

public class PersonMapper {
    public static Person mapToPerson(Long id, PersonDto personDto) {
        return createPersonBuilder(personDto)
                .withId(id)
                .build();
    }
    public static Person mapToPersonWithoutId(PersonDto personDto) {
        return createPersonBuilder(personDto)
                .build();
    }
    public static Set<Person> mapToPersons(Set<PersonDto> personList) {
        return personList
                .stream()
                .map(personDto -> mapToPerson(personDto.getId(), personDto))
                .collect(Collectors.toSet());
    }

    public static Person mapToPersonWithAdresses(PersonDto personDto) {
        return createPersonBuilder(personDto)
                .withAdresses(mapToAdresses(personDto.getAdressesDto()))
                .build();
    }
    public static Person mapToPersonFromPersonRequestDto(Long id, PersonRequestDto personRequestDto) {
        return Person.PersonBuilder.aPerson()
                .withId(id)
                .withFirstName(personRequestDto.firstName())
                .withLastName(personRequestDto.lastName())
                .withBirthDate(personRequestDto.birthDate())
                .build();
    }
    private static Person.PersonBuilder createPersonBuilder(PersonDto personDto) {
        return Person.PersonBuilder.aPerson()
                .withFirstName(personDto.getFirstName())
                .withLastName(personDto.getLastName())
                .withBirthDate(personDto.getBirthDate());
    }

}
