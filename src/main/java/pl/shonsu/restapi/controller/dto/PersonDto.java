package pl.shonsu.restapi.controller.dto;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public class PersonDto {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public PersonDto(long id, String firstName, String lastName, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
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

    public static <PersonDto> PersonDtoBuilder<PersonDto> builder() {
        return new PersonDtoBuilder<PersonDto>();
    }

    public static class PersonDtoBuilder<T> {
        private long id;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;

        private PersonDtoBuilder() {}

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
        public PersonDto build() {
            return new PersonDto(id, firstName, lastName, birthDate);
        }

        @java.lang.Override public String toString() {
            return "ExampleBuilder(id = " + id + ", firstName = " + firstName + ")";
        }

    }
}
