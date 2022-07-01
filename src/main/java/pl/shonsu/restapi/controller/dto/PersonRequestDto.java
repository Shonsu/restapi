package pl.shonsu.restapi.controller.dto;

import java.time.LocalDate;

public record PersonRequestDto(String firstName, String lastName, LocalDate birthDate) {
}
