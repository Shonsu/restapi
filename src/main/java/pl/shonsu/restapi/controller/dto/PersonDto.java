package pl.shonsu.restapi.controller.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public class PersonDto {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Set<AdressDto> adressesDto;

    public PersonDto(long id, String firstName, String lastName, LocalDate birthDate, Set<AdressDto> adressesDto) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.adressesDto = adressesDto;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    private Set<AdressDto> getAdressesDto() {
        return adressesDto;
    }

    private void addAdress(AdressDto adress) {
        this.getAdressesDto().add(adress);
    }

    public static PersonDtoBuilder builder() {
        return new PersonDtoBuilder();
    }

    public static class PersonDtoBuilder {

        private long id;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private Set<AdressDto> adressesDto = new HashSet<>();

        private PersonDtoBuilder() {
        }

        public Set<AdressDto> getAdressesDto() {
            return adressesDto;
        }

        public PersonDtoBuilder addAdressDto(AdressDto adressDto) {
            this.getAdressesDto().add(adressDto);
            return this;
        }

        public PersonDtoBuilder addListOfAdressesDto(Set<AdressDto> adressDto) {
            this.adressesDto = adressDto;
            return this;
        }

        public PersonDtoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public PersonDtoBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonDtoBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonDtoBuilder birthDatge(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public PersonDtoBuilder adressDto(Set<AdressDto> adressDto) {
            this.adressesDto = adressDto;
            return this;
        }

        public PersonDto build() {
            return new PersonDto(id, firstName, lastName, birthDate, adressesDto);
        }

        @java.lang.Override
        public String toString() {
            return "ExampleBuilder(id = " + id + ", firstName = " + firstName + "lastName" + lastName + "birthDate" + birthDate + ")";
        }

    }
}
