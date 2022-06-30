package pl.shonsu.restapi.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "adress_person",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "adress_id"))
    private Set<Adress> adresses = new HashSet<>();

    public Person() {
    }

    public Person(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public void addAdress(Adress adress) {
        this.adresses.add(adress);
        adress.getPersons().add(this);
    }

    public void removeAdress(Adress adress) {
        this.adresses.remove(adress);
        adress.getPersons().remove(this);
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

    public Set<Adress> getAdresses() {
        return adresses;
    }

    public void setAdresses(Set<Adress> adresses) {
        this.adresses = adresses;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", adresses=" + adresses +
                '}';
    }

    public static final class PersonBuilder {
        private long id;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private Set<Adress> adresses;

        private PersonBuilder() {
        }

        public static PersonBuilder aPerson() {
            return new PersonBuilder();
        }

        public PersonBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public PersonBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder withBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public PersonBuilder withAdresses(Set<Adress> adresses) {
            this.adresses = adresses;
            return this;
        }
        public Person build() {
            Person person = new Person();
            person.birthDate = this.birthDate;
            person.firstName = this.firstName;
            person.id = this.id;
            person.adresses = this.adresses;
            person.lastName = this.lastName;
            return person;
        }
    }
}
