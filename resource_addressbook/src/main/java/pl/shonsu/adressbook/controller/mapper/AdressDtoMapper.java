package pl.shonsu.adressbook.controller.mapper;

import pl.shonsu.adressbook.controller.dto.AdressDto;
import pl.shonsu.adressbook.model.Adress;
import pl.shonsu.adressbook.model.Person;

import java.util.Set;
import java.util.stream.Collectors;

public class AdressDtoMapper {
    public static Set<AdressDto> mapToAdressesDto(Set<Adress> adressList) {
        return adressList.stream()
                .map(AdressDtoMapper::mapToAdressDto)
                .collect(Collectors.toSet());
    }
    public static AdressDto mapToAdressDto(Adress adress) {
        return crateAdressDtoBuilder(adress)
                .build();
    }

    public static AdressDto mapToAdressDtoWithPersons(Adress adress, Set<Person> persons) {
        return crateAdressDtoBuilder(adress)
                .withPersonsDto(PersonDtoMapper.mapToPersonsDto(persons))
                .build();
    }

    private static AdressDto.AdressDtoBuilder crateAdressDtoBuilder(Adress adress) {
        return AdressDto.AdressDtoBuilder.anAdressDto()
                .withId(adress.getId())
                .withCity(adress.getCity())
                .withStreet(adress.getStreet())
                .withHouseNumber(adress.getHouseNumber())
                .withFlatNumber(adress.getFlatNumber());
    }

}
