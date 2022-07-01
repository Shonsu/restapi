package pl.shonsu.restapi.controller.mapper;

import pl.shonsu.restapi.controller.dto.AdressDto;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.model.Person;

import java.util.Set;
import java.util.stream.Collectors;

import static pl.shonsu.restapi.controller.mapper.PersonDtoMapper.mapToPersonsDto;

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
                .withPersonsDto(mapToPersonsDto(persons))
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
