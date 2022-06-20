package pl.shonsu.restapi.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class AdressDto {
    private long id;
    private String city;
    private String street;
    private String houseNumber;
    private Integer flatNumber;

    private List<PersonDto> personsDto;

    public AdressDto(long id, String city, String street, String houseNumber, Integer flatNumber, List<PersonDto> personsDto) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
        this.personsDto = personsDto;
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

    public List<PersonDto> getPersonsDto() {
        return personsDto;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static <AdressDto> AdressDtoBuilder<AdressDto> bulider() {
        return new AdressDtoBuilder<>();
    }

    public static class AdressDtoBuilder<Adress> {

        private long id;
        private String city;
        private String street;
        private String houseNumber;
        private Integer flatNumber;

        private List<PersonDto> personsDto = new ArrayList<>();

        private AdressDtoBuilder() {
        }

        public List<PersonDto> getPersonsDto() {
            return personsDto;
        }

        public AdressDtoBuilder<Adress> addPersonDto(PersonDto personDto) {
            this.getPersonsDto().add(personDto);
            return this;
        }

        public AdressDtoBuilder<Adress> id(long id) {
            this.id = id;
            return this;
        }

        public AdressDtoBuilder<Adress> city(String city) {
            this.city = city;
            return this;
        }

        public AdressDtoBuilder<Adress> street(String street) {
            this.street = street;
            return this;
        }

        public AdressDtoBuilder<Adress> houseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public AdressDtoBuilder<Adress> flatNumber(Integer flatNumber) {
            this.flatNumber = flatNumber;
            return this;
        }

        public AdressDtoBuilder<Adress> personsDto(List<PersonDto> personsDto) {
            this.personsDto = personsDto;
            return this;
        }

        public AdressDto bulid() {
            return new AdressDto(id, city, street, houseNumber, flatNumber, personsDto);
        }

        @java.lang.Override
        public String toString() {
            return "ExampleBuilder(id = " + id + ", city = " + city + "street" + street + "houseNumber" + houseNumber + "flatNumber" + flatNumber + "personsDto" + personsDto + ")";
        }
    }
}
