package pl.shonsu.restapi.controller.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
public class PersonDto {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private List<AdressDto> adressesDto;

    public PersonDto(long id, String firstName, String lastName, LocalDate birthDate, List<AdressDto> adressesDto) {
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

    public List<AdressDto> getAdressesDto() {
        return adressesDto;
    }

    public void addAdress(AdressDto adress) {
        this.getAdressesDto().add(adress);
    }

    public static <PersonDto> PersonDtoBuilder<PersonDto> builder() {
        return new PersonDtoBuilder<>();
    }

    public static class PersonDtoBuilder<Person> {

        private long id;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private List<AdressDto> adressesDto = new ArrayList<>();

        private PersonDtoBuilder() {
        }

        public List<AdressDto> getAdressesDto() {
            return adressesDto;
        }

        public PersonDtoBuilder<Person> addAdressDto(AdressDto adressDto) {
            this.getAdressesDto().add(adressDto);
            return this;
        }

        public PersonDtoBuilder<Person> addListOfAdressesDto(List<AdressDto> adressDto) {
            this.adressesDto = adressDto;
            return this;
        }

        public PersonDtoBuilder<Person> id(long id) {
            this.id = id;
            return this;
        }

        public PersonDtoBuilder<Person> firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonDtoBuilder<Person> lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonDtoBuilder<Person> birthDatge(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public PersonDtoBuilder<Person> adressDto(List<AdressDto> adressDto) {
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
