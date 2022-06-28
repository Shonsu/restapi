package pl.shonsu.restapi.controller.dto;

import java.util.Set;

public class AdressDto {
    private long id;
    private String city;
    private String street;
    private String houseNumber;
    private Integer flatNumber;

    public void setPersonsDto(Set<PersonDto> personsDto) {
        this.personsDto = personsDto;
    }

    private Set<PersonDto> personsDto;

    public AdressDto() {
    }

    public AdressDto(String city, String street, String houseNumber, Integer flatNumber) {
        this.city = city;
        this.street = street;
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
        private long id;
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
