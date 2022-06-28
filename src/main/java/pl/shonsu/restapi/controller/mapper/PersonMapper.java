package pl.shonsu.restapi.controller.mapper;

import pl.shonsu.restapi.controller.dto.PersonDto;
import pl.shonsu.restapi.model.Person;

import java.util.Set;
import java.util.stream.Collectors;

import static pl.shonsu.restapi.controller.mapper.AdressMapper.mapToAdresses;

public class PersonMapper {
    public static Person mapToPerson(Long id, PersonDto personDto) {
        return createPersonDtoBuilder(personDto)
                .withId(id)
                .build();
    }

    public static Set<Person> mapToPersons(Set<PersonDto> personList) {
        return personList
                .stream()
                .map(personDto -> mapToPerson(personDto.getId(), personDto))
                .collect(Collectors.toSet());
    }

    public static Person mapToPersonWithAdresses(PersonDto personDto) {
        return createPersonDtoBuilder(personDto)
                .withAdresses(mapToAdresses(personDto.getAdressesDto()))
                .build();
    }

    public static PersonDto mapToPersonDto(Person person) {
        return PersonDto.PersonDtoBuilder.aPersonDto()
                .withFirstName(person.getFirstName())
                .withLastName(person.getLastName())
                .withBirthDate(person.getBirthDate())
                .build();
    }
    private static Person.PersonBuilder createPersonDtoBuilder( PersonDto personDto) {
        return Person.PersonBuilder.aPerson()
                .withFirstName(personDto.getFirstName())
                .withLastName(personDto.getLastName())
                .withBirthDate(personDto.getBirthDate());
    }

}
