package pl.shonsu.adressbook.controller.dto;


import javax.validation.constraints.*;

public record AdressRequestDto(
        @NotBlank(message = "City can not be empty")
        @NotNull(message = "Adress must have City")
        @Pattern(regexp = "[a-zA-Z -]*", message = "City can only have letters")
        String city,
        @NotBlank(message = "Street can not be empty")
        @NotNull(message = "Adress must have street")
        @Pattern(regexp = "[a-zA-Z -.]*", message = "Street can only have letters")
        String street,
        @NotNull(message = "Adress must have house number")
        String houseNumber,
        @Positive(message = "Number must be positive.")
        Integer flatNumber) {
}
