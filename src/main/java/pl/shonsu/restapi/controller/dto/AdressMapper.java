package pl.shonsu.restapi.controller.dto;

import org.mapstruct.Mapper;
import pl.shonsu.restapi.model.Adress;

@Mapper
public interface AdressMapper {
    AdressDto2 mapToAdressDto2(Adress adress);
    Adress mapToAdress(AdressDto2 adressDto2);
}
