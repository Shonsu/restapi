package pl.shonsu.restapi.controller.dto;

import pl.shonsu.restapi.model.Person;

import java.util.List;

public class PersonDtoMapper {

    private PersonDtoMapper() {
    }

    public static List<PersonDto> mapToPersonDtos(List<Person> persons) {
        return persons.stream()
                .map(person -> mapToPersonDto(person))
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

    public static PersonDto mapToPersonDtoWithAdress(Person person, AdressDto adress) {
        return PersonDto.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .birthDatge(person.getBirthDate())
                .addAdressDto(adress)
                .build();
    }

    public static PersonDto mapToPersonDtoWithAdresses(Person person, List<AdressDto> adresses) {
        return PersonDto.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .birthDatge(person.getBirthDate())
                .addListOfAdressesDto(adresses)
                .build();
    }
}
