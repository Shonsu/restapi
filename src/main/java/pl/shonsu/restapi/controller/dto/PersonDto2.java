package pl.shonsu.restapi.controller.dto;

import java.time.LocalDate;
import java.util.Set;

public record PersonDto2(long id,
                         String firstName,
                         String lastName,
                         LocalDate birthDate,
                         Set<AdressDto2> adresses) {
}
