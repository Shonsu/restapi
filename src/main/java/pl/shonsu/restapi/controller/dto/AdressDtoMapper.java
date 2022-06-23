package pl.shonsu.restapi.controller.dto;

import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;

import java.util.Set;
import java.util.stream.Collectors;

public class AdressDtoMapper {
    private AdressDtoMapper() {
    }

    public static Set<AdressDto> mapToAdressDtos(Set<Adress> adresses) {
        return adresses.stream().map(adress -> mapToAdressDto(adress)).collect(Collectors.toSet());
    }

    public static AdressDto mapToAdressDto(Adress adress) {
        return createAdressDtoBuilder(adress).bulid();
    }

    public static AdressDto mapToAdressDtoWithPerson(Adress adress, Person person) {
        PersonDto personDto = PersonDtoMapper.mapToPersonDto(person);
        return createAdressDtoBuilder(adress).addPersonDto(personDto).bulid();
    }

    private static AdressDto.AdressDtoBuilder createAdressDtoBuilder(Adress adress) {
        return AdressDto.bulider()
                .id(adress.getId())
                .city(adress.getCity())
                .street(adress.getStreet())
                .flatNumber(adress.getFlatNumber())
                .houseNumber(adress.getHouseNumber());
    }

}