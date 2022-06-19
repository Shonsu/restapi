package pl.shonsu.restapi.controller.dto;

import lombok.Builder;
import pl.shonsu.restapi.model.Adress;

import java.time.LocalDate;
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
        adress.getPersonsDto().add(this);
    }
    public static <PersonDto> PersonDtoBuilder<PersonDto> builder() {
        return new PersonDtoBuilder<PersonDto>();
    }
    public static class PersonDtoBuilder<Person> {

        private long id;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private List<AdressDto> adressesDto;

        public List<AdressDto> getAdressesDto() {
            return adressesDto;
        }

        public void setAdressesDto(List<AdressDto> adressesDto) {
            this.adressesDto = adressesDto;
        }

        public PersonDtoBuilder addAdressDto(AdressDto adressDto){
            this.getAdressesDto().add(adressDto);
            adressDto.getPersonsDto().add(super.Person);
            return this;
        }
        private PersonDtoBuilder() {
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
        public PersonDtoBuilder adressDto(List<PersonDto> adressDto) {
            this.adressesDto = adressesDto;
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
