package pl.shonsu.restapi.controller.dto;

import org.mapstruct.*;
import pl.shonsu.restapi.model.Adress;

@Mapper(uses = {PersonMapper.class}, componentModel = "spring")
public interface AdressMapper {

    @Named("mapToAdress")
    @Mapping(source = "personsDto2", target = "persons")
    Adress mapToAdress(AdressDto2 adressDto2);

    @InheritInverseConfiguration(name = "mapToAdress")
    AdressDto2 mapToAdressDto2(Adress adress);

    @InheritConfiguration(name = "mapToAdress")
    @Mapping(ignore = true, target = "persons")
    Adress mapToAdressWithoutPersons(AdressDto2 adressDto2);
}
