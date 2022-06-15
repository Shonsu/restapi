package pl.shonsu.restapi.controller.dto;

import pl.shonsu.restapi.model.Person;

import java.util.List;

public class PersonDtoMapper {

    private PersonDtoMapper() {
    }

    public static List<PersonDto> mapToPersonDtos(List<Person> persons) {
        return persons.stream()
                .map(person-> mapToPersonDto(person))
                .toList();
    }

    public static PersonDto mapToPersonDto(Person person) {
        return PersonDto.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .birthDatge(person.getBirthDate())
                .build();
    }
}
