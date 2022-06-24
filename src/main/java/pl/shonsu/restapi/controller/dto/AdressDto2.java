package pl.shonsu.restapi.controller.dto;

import java.util.Set;

public record AdressDto2(long id,
                         String city,
                         String street,
                         String houseNumber,
                         Integer flatNumber,
                         Set<PersonDto2> personsDto2) {
}
