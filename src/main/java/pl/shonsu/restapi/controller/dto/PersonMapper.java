package pl.shonsu.restapi.controller.dto;

import org.mapstruct.Mapper;
import pl.shonsu.restapi.model.Person;

@Mapper
public interface PersonMapper {
    Person mapToPerson(PersonDto personDto);
    PersonDto mapToPersonDto(Person person);
}
