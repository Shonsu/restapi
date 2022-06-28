package pl.shonsu.restapi.controller.mapper;

import pl.shonsu.restapi.controller.dto.PersonDto;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;

import java.util.Set;
import java.util.stream.Collectors;

import static pl.shonsu.restapi.controller.mapper.AdressDtoMapper.mapToAdressesDto;

public class PersonDtoMapper {
    public static Set<PersonDto> mapPersonToPersonDto(Set<Person> persons) {
        return persons.stream()
                .map(person -> PersonDto.PersonDtoBuilder.aPersonDto()
                        .withId(person.getId())
                        .withFirstName(person.getFirstName())
                        .withLastName(person.getLastName())
                        .withBirthDate(person.getBirthDate())
                        .build())
                .collect(Collectors.toSet());
    }

    public static PersonDto mapToPersonDtoWithAdresses(Person person, Set<Adress> adresses) {
        return PersonDto.PersonDtoBuilder.aPersonDto()
                .withId(person.getId())
                .withFirstName(person.getFirstName())
                .withLastName(person.getLastName())
                .withBirthDate(person.getBirthDate())
                .withAdressesDto(mapToAdressesDto(adresses))
                .build();
    }
//    private static PersonDto mapToPersonDtoWithAdresses(Person person) {
//        return PersonDto.PersonDtoBuilder.aPersonDto()
//                .withId(person.getId())
//                .withFirstName(person.getFirstName())
//                .withLastName(person.getLastName())
//                .withBirthDate(person.getBirthDate())
//                .withAdressesDto(mapToAdressesDto(person.getAdresses()))
//                .build();
//    }
}
