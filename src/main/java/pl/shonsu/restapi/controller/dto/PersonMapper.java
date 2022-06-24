package pl.shonsu.restapi.controller.dto;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.shonsu.restapi.model.Person;

@Mapper(uses = {AdressMapper.class}, componentModel = "spring")
public interface PersonMapper {
    @Mapping(source = "adressesDto2", target = "adresses", qualifiedByName = "mapToAdress")
    Person mapToPerson(PersonDto2 personDto);

    @InheritInverseConfiguration(name = "mapToPerson")
    PersonDto2 mapToPersonDto2(Person person);
}
