package pl.shonsu.restapi.controller.mapper;

import pl.shonsu.restapi.controller.dto.PersonDto;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.shonsu.restapi.controller.mapper.AdressDtoMapper.mapToAdressesDto;

public class PersonDtoMapper {
    public static Set<PersonDto> mapToPersonsDto(Set<Person> persons) {
        return persons.stream()
                .map(person -> createPersonDtoBuilder(person)
                        .build())
                .collect(Collectors.toSet());
    }

    public static PersonDto mapToPersonDto(Person person) {
        return createPersonDtoBuilder(person)
                .build();
    }

    public static PersonDto mapToPersonDtoWithAdresses(Person person, Set<Adress> adresses) {
        return createPersonDtoBuilder(person)
                .withAdressesDto(mapToAdressesDto(adresses))
                .build();
    }
    public static List<PersonDto> mapToPersonsDtoWithAdresses(List<Person> personList) {
        return personList.stream()
                .map(person -> mapToPersonDtoWithAdresses(person, person.getAdresses()))
                .toList();
    }
    private static PersonDto.PersonDtoBuilder createPersonDtoBuilder(Person person) {
        return PersonDto.PersonDtoBuilder.aPersonDto()
                .withId(person.getId())
                .withFirstName(person.getFirstName())
                .withLastName(person.getLastName())
                .withBirthDate(person.getBirthDate());
    }
}
