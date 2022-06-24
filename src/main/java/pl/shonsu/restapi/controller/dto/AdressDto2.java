package pl.shonsu.restapi.controller.dto;

import java.util.List;

public record AdressDto2(long id,
                         String city,
                         String street,
                         String houseNumber,
                         Integer flatNumber,
                         List<PersonDto2> persons) {
}
