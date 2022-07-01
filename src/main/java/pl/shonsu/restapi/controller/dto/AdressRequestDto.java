package pl.shonsu.restapi.controller.dto;

public record AdressRequestDto(String city,
                               String street,
                               String houseNumber,
                               Integer flatNumber) {
}
