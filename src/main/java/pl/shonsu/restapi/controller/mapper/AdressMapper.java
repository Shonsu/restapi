package pl.shonsu.restapi.controller.mapper;

import pl.shonsu.restapi.controller.dto.AdressDto;
import pl.shonsu.restapi.controller.dto.AdressRequestDto;
import pl.shonsu.restapi.model.Adress;

import java.util.Set;
import java.util.stream.Collectors;

public class AdressMapper {
    public static Adress mapToAdress(Long id, AdressDto adressDto) {
        return Adress.AdressBuilder.anAdress()
                .withId(id)
                .withCity(adressDto.getCity())
                .withStreet(adressDto.getStreet())
                .withHouseNumber(adressDto.getHouseNumber())
                .withFlatNumber(adressDto.getFlatNumber())
                .build();
    }

    public static Set<Adress> mapToAdresses(Set<AdressDto> adressesDto) {
        return adressesDto.stream().
                map(adressDto -> mapToAdress(adressDto.getId(), adressDto))
                .collect(Collectors.toSet());
    }

    public static Adress mapToAdressFromAdressRequestDto(Long id, AdressRequestDto adressRequestDto) {
        return Adress.AdressBuilder.anAdress()
                .withId(id)
                .withCity(adressRequestDto.city())
                .withStreet(adressRequestDto.street())
                .withHouseNumber(adressRequestDto.houseNumber())
                .withFlatNumber(adressRequestDto.flatNumber())
                .build();
    }
}
