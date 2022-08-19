package pl.shonsu.clientserver.dtos;

import java.util.HashSet;
import java.util.Set;

public class AdressDto {
    private Long id;
//    @NotBlank(message = "City can not be empty")
//    @NotNull(message = "Adress must have City")
//    @Pattern(regexp = "[a-zA-Z -]*", message = "City can only have letters")
    private String city;
//    @NotBlank(message = "Street can not be empty")
//    @NotNull(message = "Adress must have street")
//    @Pattern(regexp = "[a-zA-Z -.]*", message = "Street can only have letters")
    private String street;
  //  @NotNull(message = "Adress must have house number")
    private String houseNumber;
  //  @Positive(message = "Number must be positive")
    private Integer flatNumber;

    private Set<PersonDto> personsDto = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

    public void setPersonsDto(Set<PersonDto> personsDto) {
        this.personsDto = personsDto;
    }

    public AdressDto() {
    }

    public AdressDto(String city, String street, String houseNumber, Integer flatNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }

    public long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public Set<PersonDto> getPersonsDto() {
        return personsDto;
    }

    public static final class AdressDtoBuilder {
        private Long id;
        private String city;
        private String street;
        private String houseNumber;
        private Integer flatNumber;
        private Set<PersonDto> personsDto;

        private AdressDtoBuilder() {
        }

        public static AdressDtoBuilder anAdressDto() {
            return new AdressDtoBuilder();
        }

        public AdressDtoBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public AdressDtoBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public AdressDtoBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public AdressDtoBuilder withHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public AdressDtoBuilder withFlatNumber(Integer flatNumber) {
            this.flatNumber = flatNumber;
            return this;
        }

        public AdressDtoBuilder withPersonsDto(Set<PersonDto> personsDto) {
            this.personsDto = personsDto;
            return this;
        }

        public AdressDto build() {
            AdressDto adressDto = new AdressDto();
            adressDto.street = this.street;
            adressDto.houseNumber = this.houseNumber;
            adressDto.city = this.city;
            adressDto.flatNumber = this.flatNumber;
            adressDto.personsDto = this.personsDto;
            adressDto.id = this.id;
            return adressDto;
        }
    }
}
