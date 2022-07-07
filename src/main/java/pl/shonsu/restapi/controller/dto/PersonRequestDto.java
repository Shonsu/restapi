package pl.shonsu.restapi.controller.dto;

import javax.validation.constraints.*;
import java.time.LocalDate;

public record PersonRequestDto(
        @NotBlank(message = "User can not have empty Last name")
        @NotNull(message = "User must have Last name")
        @Pattern(regexp = "[a-zA-Z -]*", message = "Name can only have letters")
        String firstName,
        @NotBlank(message = "User can not have empty Last name")
        @NotNull(message = "User must have Last name")
        @Pattern(regexp = "[a-zA-Z -]*", message = "Name can only have letters")
        String lastName,
        @Past(message = "Date of birth should be from the past")
        LocalDate birthDate) {
}
