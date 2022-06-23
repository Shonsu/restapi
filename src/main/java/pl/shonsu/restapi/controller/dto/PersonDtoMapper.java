package pl.shonsu.restapi.controller.dto;

import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;

import java.util.List;
import java.util.Set;

public class PersonDtoMapper {

    private PersonDtoMapper() {
    }

    public static List<PersonDto> mapToPersonDtos(List<Person> persons) {
        return persons.stream()
                .map(person -> mapToPersonDto(person))
                .toList();
    }


    public static PersonDto mapToPersonDto(Person person) {
        return createPersonDtoBuilder(person)
                .build();
    }

    public static PersonDto mapToPersonDtoWithAdress(Person person, Adress adress) {
        AdressDto adressDto = AdressDtoMapper.mapToAdressDto(adress);
        return createPersonDtoBuilder(person)
                .addAdressDto(adressDto)
                .build();
    }

    public static PersonDto mapToPersonDtoWithAdresses(Person person, Set<AdressDto> adresses) {
        return createPersonDtoBuilder(person)
                .addListOfAdressesDto(adresses)
                .build();
    }

    private static PersonDto.PersonDtoBuilder createPersonDtoBuilder(Person person) {
        return PersonDto.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .birthDatge(person.getBirthDate());
    }
}
