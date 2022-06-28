package pl.shonsu.restapi.controller.mapper;

import pl.shonsu.restapi.controller.dto.AdressDto;
import pl.shonsu.restapi.model.Adress;

import java.util.Set;
import java.util.stream.Collectors;

public class AdressDtoMapper {
    public static Set<AdressDto> mapToAdressesDto(Set<Adress> adressList) {
        return adressList.stream()
                .map(adress -> AdressDto.AdressDtoBuilder.anAdressDto()
                        .withId(adress.getId())
                        .withCity(adress.getCity())
                        .withStreet(adress.getStreet())
                        .withHouseNumber(adress.getHouseNumber())
                        .withFlatNumber(adress.getFlatNumber())
                        .build())
                .collect(Collectors.toSet());
    }

}
