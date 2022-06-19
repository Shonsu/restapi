package pl.shonsu.restapi.controller.dto;

import pl.shonsu.restapi.model.Adress;

import java.util.List;

public class AdressDtoMapper {
    private AdressDtoMapper() {
    }

    public static List<AdressDto> mapToAdressDtos(List<Adress> adresses) {
        return adresses.stream().map(adress -> mapToAdressDto(adress)).toList();
    }

    public static AdressDto mapToAdressDto(Adress adress) {
        return AdressDto.bulider()
                .id(adress.getId())
                .city(adress.getCity())
                .street(adress.getStreet())
                .flatNumber(adress.getFlatNumber())
                .houseNumber(adress.getHouseNumber())
                .bulid();
    }
    public static AdressDto mapToAdressDtoWithPerson(Adress adress, PersonDto personDto) {
        return AdressDto.bulider()
                .id(adress.getId())
                .city(adress.getCity())
                .street(adress.getStreet())
                .flatNumber(adress.getFlatNumber())
                .houseNumber(adress.getHouseNumber())
                .addPersonDto(personDto)
                .bulid();
    }

}
