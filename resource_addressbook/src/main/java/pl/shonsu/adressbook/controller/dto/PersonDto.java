package pl.shonsu.adressbook.controller.dto;

import pl.shonsu.adressbook.model.Person;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PersonDto {
    private Long id;
    @NotBlank(message = "User can not have empty First name")
    @NotNull(message = "User must have First name")
    @Pattern(regexp = "[a-zA-Z -]*", message = "Name can only have letters")
    private String firstName;

    @NotBlank(message = "User can not have empty Last name")
    @NotNull(message = "User must have Last name")
    @Pattern(regexp = "[a-zA-Z -]*", message = "Name can only have letters")
    private String lastName;

    @Past(message = "Date of birth should be from the past")
    private LocalDate birthDate;

    private Set<AdressDto> adressesDto = new HashSet<>();

    public PersonDto(){}

    public PersonDto(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }


    public PersonDto(Person person) {
    }

    public Long getId() {
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

    public Set<AdressDto> getAdressesDto() {
        return adressesDto;
    }


//    public void addAddres(AdressDto adressDto){
//        this.adressesDto.add(adressDto);
//        adressDto.getPersonsDto().add(this);
//    }
    public void setAdressesDto(Set<AdressDto> adressesDto) {
        this.adressesDto = adressesDto;
    }
    public static final class PersonDtoBuilder {

        private Long id;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private Set<AdressDto> adressesDto;

        private PersonDtoBuilder() {
        }

        public static PersonDtoBuilder aPersonDto() {
            return new PersonDtoBuilder();
        }

        public PersonDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PersonDtoBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonDtoBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonDtoBuilder withBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public PersonDtoBuilder withAdressesDto(Set<AdressDto> adressesDto) {
            this.adressesDto = adressesDto;
            return this;
        }

        public PersonDto build() {
            PersonDto personDto = new PersonDto();
            personDto.lastName = this.lastName;
            personDto.id = this.id;
            personDto.birthDate = this.birthDate;
            personDto.adressesDto = this.adressesDto;
            personDto.firstName = this.firstName;
            return personDto;
        }
    }
}
